package com.wide.base;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.wide.exception.IncorrectCaptchaException;
import com.wide.common.model.CaptchaUsernamePasswordToken;
import com.wide.common.model.Right;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.common.model.simple.SimpleUser;
import com.wide.util.captcha.CaptchaRender;
import com.jfinal.kit.StrKit;


public class ShiroDbRealm extends AuthorizingRealm {
    
    public ShiroDbRealm(){
        setAuthenticationTokenClass(CaptchaUsernamePasswordToken.class);
    }

    /**
     * 认证回调函数,登录时调用.
     */  
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        CaptchaUsernamePasswordToken authcToken = (CaptchaUsernamePasswordToken) token;
        String loginName = authcToken.getUsername();
        char[] password = authcToken.getPassword();
        if (StrKit.isBlank(loginName)) {
            throw new AuthenticationException("用户名不可以为空");
        }
        /**
        boolean isCaptchaBlank = StrKit.isBlank(authcToken.getCaptcha());
        if (isCaptchaBlank) {
            throw new IncorrectCaptchaException("验证码不可以为空!");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        String md5Code = null;
        if(session != null){
            md5Code = (String)session.getAttribute(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY);
        }
        boolean isRight = CaptchaRender.validate(md5Code, authcToken.getCaptcha());
        if (!isRight) {
            throw new IncorrectCaptchaException("验证码错误!");
        }*/
        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(new String(password));
        List<User> userlist = User.dao.findLogin(user);
        if (!(userlist.size()>0)) {
            throw new AuthenticationException("用户名或者密码错误");
        }else{
        	user = userlist.get(0);
        	if (userlist.get(0).getDelFlag().equals("1")) {
                throw new LockedAccountException("该用户已被锁定");
            }
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
        return  info;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //User user = (User) principals.fromRealm(getName()).iterator().next();
    	String loginname =principals.fromRealm(getName()).iterator().next()+"";
    	User user = User.dao.findByUsername(loginname);
    	SimpleUser simpleUser = new SimpleUser(user.getId(),user.getLoginName(),user.getRemarks(),user.getUserType()+"");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = Role.dao.getRoleByuserID(simpleUser.getId());
        if(roles!=null&&roles.size()>0){
            for(Role role : roles){
                //角色的名称及时角色的值
                info.addRole(role.getEnname());
                addResourceOfRole(role,info);
            }
        }
        return info;
    }
    
    
    private void addResourceOfRole(Role role, SimpleAuthorizationInfo info){
    	List<Right> resources = Right.dao.getRoleRightByroleId(role.getId());
        if(resources!=null&&resources.size()>0){
            for(Right r : resources ){
            	if(r.getPermission()!=null&&!r.getPermission().equals("")){
            		//资源代码就是权限值，类似user：list
                    info.addStringPermission(r.getPermission());
            	}
            }
        }
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
