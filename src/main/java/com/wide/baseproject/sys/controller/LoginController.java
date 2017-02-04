package com.wide.baseproject.sys.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.wide.baseproject.sys.service.LoginService;
import com.wide.baseproject.sys.service.MenuService;
import com.wide.baseproject.sys.service.OfficeService;
import com.wide.baseproject.sys.service.RoleService;
import com.wide.baseproject.sys.service.UserService;
import com.wide.common.model.CaptchaUsernamePasswordToken;
import com.wide.common.model.Menu;
import com.wide.common.model.User;
import com.wide.config.UserToken;
import com.wide.exception.IncorrectCaptchaException;
import com.wide.interceptor.CaptchaFormAuthenticationInterceptor;
import com.wide.util.captcha.CaptchaRender;
import com.wide.viewmodel.ViewUser;

public class LoginController extends Controller {
	//注入service 可以Enhancer.enhance 也可以Duang.duang()
	public static final LoginService loginService = Enhancer.enhance(LoginService.class);
	private static final MenuService menuService = Enhancer.enhance(MenuService.class);
	private static final OfficeService officeService =Enhancer.enhance(OfficeService.class);
	private static final RoleService roleService = Enhancer.enhance(RoleService.class);
	private static final UserService userService = Enhancer.enhance(UserService.class);
	private static final Logger LOG = Logger.getLogger(LoginController.class);
    private static final int DEFAULT_CAPTCHA_LEN = 4;
    private static final String LOGIN_URL = "login.jsp";
	/**
	 * 跳转登录页
	 */
	@Clear
	public void index(){
		this.createToken("loginToken");
		render("login.jsp");
	}
	/**
	 * 跳转错误页面
	 */
	@Clear
	public void errorPage(){
		render("error.jsp");
	}
	 /**
     * @Title: img  
     * @Description: 图形验证码   
     * @since V1.0.0
     */
	
	/*@Clear
    public void img(){
        CaptchaRender img = new CaptchaRender(DEFAULT_CAPTCHA_LEN);
        this.setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
        render(img);
        }*/
	/**
	 * 验证
	 * 
	 **/
	@Clear
    @Before(CaptchaFormAuthenticationInterceptor.class)
    public void doLogin(){
		UserToken userToken = new UserToken();
		ViewUser vuser = new ViewUser();
		Map<String,String> officenamemaps = new HashMap<String,String>();
        try {
            CaptchaUsernamePasswordToken token = this.getAttr("shiroToken");
            Subject subject = SecurityUtils.getSubject();
            ThreadContext.bind(subject);
            //进行用用户名和密码验证
            subject.login(token);
            //获取当前用户，并将当前用户保存在Session中。
            User user = User.dao.findByUsername(token.getUsername());
            if(user!=null&&!user.equals("")){
            	Db.update("update sys_user set isonline = 1 where id = '"+user.getId()+"'");
	   		 }
            vuser.setUser(user);
			Map<String,String> rolenamemap = roleService.getRoleByuserID(user.getId());
			List<Menu> mlist = menuService.getMenuByuserID(user.getId());
			officenamemaps = officeService.getOfficeByuserID(user.getId());
			vuser.setOfficenames(officenamemaps);
			vuser.setRolenames(rolenamemap);
			userToken.setVuser(vuser);
			userToken.setMenulist(mlist);
			setSessionAttr("userToken", userToken);
            //调转到主页面
            setAttr("user",user);
            redirect("/user/mainindex");
            
        } catch (IncorrectCaptchaException e) {
        	LOG.error(e.getMessage());
            setAttr("msg","验证码错误");
            render(LOGIN_URL);
        } catch (LockedAccountException e) {
        	LOG.error(e.getMessage());
            setAttr("msg","账号已被锁定");
            render(LOGIN_URL);
        } catch (AuthenticationException e) {
        	LOG.error(e.getMessage());
            setAttr("msg","用户名或者密码错误");
            render(LOGIN_URL);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            setAttr("msg","系统异常");
            render(LOGIN_URL);
        }
        
    }
	/**
	 * 注销方法
	 */
    @RequiresAuthentication
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        try { 
        	 HttpSession session = getSession();
	   		 UserToken ut = (UserToken) session.getAttribute("userToken");
	   		 if(ut!=null&&!ut.equals("")){
	   			 Db.update("update sys_user set isonline = 0 where id = ?",ut.getVuser().getUser().getId());
	   		 }
        	removeSessionAttr("UserToken"); 
            currentUser.logout();
            render(LOGIN_URL);
        } catch (SessionException ise) {
            LOG.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        } catch (Exception e) {
            LOG.debug("登出发生错误", e);
        }
    }
    /**
	 * 关闭浏览器
	 */
    @Clear
	public void closeout(){
		 HttpSession session = getSession();
   		 UserToken ut = (UserToken) session.getAttribute("userToken");
   		 if(ut!=null&&!ut.equals("")){
   			 Db.update("update sys_user set isonline = 0 where id = ?",ut.getVuser().getUser().getId());
   		 }
   		 renderJson();
	}
	
	/**
	 * 用户登录验证用户名和密码，结果返回登录页面  1 未填写  2密码或用户名错误  0正常
	 */
	@Clear
	public void checklogin(){
		String error = "0";		
		Boolean  u =  true;
		String loginname =getPara("loginname");		
		String password =getPara("password");
		if(loginname==null||loginname.equals("")){
			error = "1";
		}
		if(password==null||password.equals("")){
			error = "1";
		}
		if(!loginname.equals("")&&!password.equals("")){
			 u =userService.getUserBylogininfo(loginname,password);
			 if(u==false){
				 error = "2"; 
			 }
		}
					
		renderJson(error);
	}

}
