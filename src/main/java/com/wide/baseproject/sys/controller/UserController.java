package com.wide.baseproject.sys.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.json.JFinalJson;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.exam.service.ExamService;
import com.wide.baseproject.resource.controller.ItemBankController;
import com.wide.baseproject.statistics.service.StatisticsService;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.LogService;
import com.wide.baseproject.sys.service.OfficeService;
import com.wide.baseproject.sys.service.RoleService;
import com.wide.baseproject.sys.service.UserService;
import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.Itembank;
import com.wide.common.model.Office;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Role;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExam;
import com.wide.common.model.query.QueryUser;
import com.wide.common.model.simple.ImportDAX;
import com.wide.common.model.simple.ImportDOX;
import com.wide.common.model.simple.ImportPD;
import com.wide.common.model.simple.ImportTK;
import com.wide.common.model.simple.ImportUser;
import com.wide.common.model.simple.ImportWD;
import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.util.ExportController;
import com.wide.util.TypeChecker;
import com.wide.validator.UserValidator;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewChartData;
import com.wide.viewmodel.ViewChartZDate;
import com.wide.viewmodel.ViewUser;

public class UserController extends BaseController {
	private static final UserService userService = Enhancer
			.enhance(UserService.class);
	private static final RoleService roleService = Enhancer
			.enhance(RoleService.class);
	private static final DictService dictService = Enhancer
			.enhance(DictService.class);
	private static final OfficeService officeService = Enhancer
			.enhance(OfficeService.class);
	private static final LogService logService = Enhancer
			.enhance(LogService.class);	

	private static final StatisticsService statisticsService = Enhancer.enhance(StatisticsService.class);

	
	/**
	 * 获取当前UserToken
	 * @return UserToken
	 */
	public UserToken getCurrentUserToken(){
		HttpSession session = getSession();          //获取当前用户
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());
		
		return ut;
		
	}	
    
	public void index() {
		// 分页
		setAttr("userPage", User.dao.paginate(getParaToInt(0, 1), 10));
		redirect("/user/mainindex");
	}
	/**
	 * @author cg 用户管理
	 */
	public void add() {
		List<Dict> listd = dictService.getDictByType("1003");
		setAttr("listdict", listd);
		render("userList.jsp");
	}

	/**
	 * @author cg Datetables分页显示
	 */
	public void addUserList() {
		QueryUser queryUser = new QueryUser();
		String usertype = getPara("usertype");
		queryUser.setUsertype(usertype == null || usertype.equals("") ? 0
				: Integer.parseInt(usertype));
		queryUser.setEndtime(getPara("endtime"));
		queryUser.setStarttime(getPara("starttime"));
		queryUser.setUsername(getPara("username"));
		queryUser.setOfficeid(getPara("officeid"));
		DataTablesModel userpage = userService.getPageUser(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryUser);
		this.renderJson(userpage);
	}

	/**
	 * @author cg 保存个人信息
	 */
	@Before(UserValidator.class)
	public void save() {
		getModel(User.class).save();
		redirect("/user");
	}

	/**
	 * @author cg 编辑个人信息
	 */
	public void edit() {
		setAttr("user", User.dao.findById(getParaToInt()));
	}

	/**
	 * @author cg 更新个人信息
	 */
	@Before(UserValidator.class)
	public void update() {
		getModel(User.class).update();
		redirect("/user");
	}

	/**
	 * @author cg 我的面板
	 */

	public void addInfo() {			
		UserToken ut = getCurrentUserToken();
		ut.getVuser().setUser(userService.getUser(ut.getVuser().getUser()));
		setAttr("userToken", ut);
		String mark = getPara("message");
		setAttr("message", mark);
		render("addinfo.jsp");
	}
	/**
	 * @author ycl 默认首页
	 */

	public void mainindex() {	
		
		List<Exam> examList = new ArrayList<Exam>();
		UserToken ut = getCurrentUserToken();
		ut.getVuser().setUser(userService.getUser(ut.getVuser().getUser()));
		setAttr("userToken", ut);
		String mark = getPara("message");
		setAttr("message", mark);
		
		if(ut.getVuser().getUser().getLoginType()==0){
			/**
			List<ViewChartData> list = new ArrayList<ViewChartData>();
			list = statisticsService.queryMyChartDatas(getUser().getId());
			setAttr("chartlist", JsonKit.toJson(list));
			examList = Exam.dao.getExamList(getUser().getId());
			setAttr("examList", examList);
			render("mainexaminee.jsp");
			 * */
			redirect("/bespeak/addschedulebespeak");
		}else{
			/**
			 List<ViewChartZDate> list = new ArrayList<ViewChartZDate>();
			list = statisticsService.queryListChartDatas();
			examList = Exam.dao.getExamList("");
			setAttr("chartlist", JsonKit.toJson(list));
			setAttr("examList", examList);
			render("adminmain.jsp"); 
			 * */
			redirect("/bespeak/add");
		}
		
	}

	/**
	 * @author cg 更新个人信息
	 */	
	public void saveInfo() {
		getModel(User.class).update();
		redirect("/user/addInfo?message=success", true);
	}

	/**
	 * @author cg 更新密码 
	 */
	public void addpass() {		
		String mark = getPara("message");
		setAttr("message", mark);
		logService.saveLog(EnumOptType.password.getEnumKey(), EnumFuncType.user.getEnumKey(),userService.getUser(getCurrentUserToken().getVuser().getUser())); //用户密码修改日志保存
		render("myEditPass.jsp");
	}

	/**
	 * @author cg 更新密码
	 */
	public void updatepass() {	
		String password = getPara("password");
		User user=getCurrentUserToken().getVuser().getUser();
		user.setPassword(new Sha256Hash(password, user.getLoginName(), 1024).toBase64());
		user.update();
		logService.saveLog(EnumOptType.password.getEnumKey(), EnumFuncType.user.getEnumKey(), userService.getUser(user)); //用户密码修改日志保存
		redirect("/user/addpass?message=success", true);
	}

	/**
	 * @author cg 验证密码
	 */
	public void checkpass() {
		HttpSession session = getSession();
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());
		String pass = new Sha256Hash(getPara("oldpassword")+"", user.getLoginName(), 1024).toBase64();
		renderJson(pass.equals(user.getPassword()) ? 1 : 2);// 1输入正确、2输入错误
	}

	/**
	 * @author cg 增加用户
	 */
	public void adduserinfo() {
		String id = getPara("id");
		User user = userService.getUserById(id);
		List<Dict> usertypelist = dictService.getDictByType("1003");
		List<Dict> logintypelist = dictService.getDictByType("1014");
		List<Dict> nationlist = dictService.getDictByType("1005");
		List<Dict> politicsstatuslist = dictService.getDictByType("1004");
		List<Role> rolelist = roleService.getRoleAll();
		List<Role> rulist = Role.dao.getRoleByuserID(id);
		List<Office> officelist = officeService.getOfficeListByuserID(id);
		String officenames = "";
		String officeids = "";
		String roleids = "";
		if (officelist.size() > 0) {
			for (Office o : officelist) {
				officenames = officenames + "|" + o.getName();
				officeids = officeids + o.getId() + "|";
			}
		}
		if (rulist.size() > 0) {
			for (Role r : rulist) {
				roleids = roleids + "|" + r.getId();
			}
		}
		String mark = getPara("message");
		setAttr("usertypelist", usertypelist);
		setAttr("nationlist", nationlist);
		setAttr("politicsstatuslist", politicsstatuslist);
		setAttr("logintypelist", logintypelist);
		setAttr("message", mark);
		setAttr("user", user);
		if(user!=null){
			setAttr("utype", user.getUserType());
		}else{
			setAttr("utype", "0");
		}
		setAttr("rolelist", rolelist);
		setAttr("offids", officeids);
		setAttr("roleids", roleids);
		setAttr("officenames", officenames);
		render("userInfo.jsp");
	}

	/**
	 * @author cg 保存用户
	 */
	//@Before(UserValidator.class)
	public void saveuserinfo() {		
		ViewUser vu = new ViewUser();	
		String roleids = getPara("roleids");
		String offids = getPara("offids");
		User user = getModel(User.class);
		user.setUpdateBy(getUser().getId());
		user.setUpdateDate(new Date());
		vu.setOfficeids(offids);
		vu.setRoleids(roleids);
		vu.setUser(user);
		if(vu.getUser().getId() != null && !vu.getUser().getId().equals("")){
			logService.saveLog(EnumOptType.edit.getEnumKey(), EnumFuncType.user.getEnumKey(), getUser()); //用户修改日志保存
		}else {
			logService.saveLog(EnumOptType.add.getEnumKey(), EnumFuncType.user.getEnumKey(), getUser()); //用户添加日志保存
		}
		userService.saveuserinfo(vu, getUser());
		
		
		redirect("/user/adduserinfo?message=success", true);
	}

	/**
	 * @author cg 删除用户
	 * */
	@Before(Tx.class)
	public boolean deluserinfo() {	
		String id = getPara("id");
		id = id == null ? "" : id;
		try {
			userService.deluserinfo(id);
			logService.saveLog(EnumOptType.del.getEnumKey(), EnumFuncType.user.getEnumKey(), userService.getUser(getCurrentUserToken().getVuser().getUser())); //用户删除日志保存
			renderJson("2");
		} catch (Exception e) {
			renderJson("1");
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * @author cg 导出用户
	 * */
	public void exportUser() {		
		QueryUser queryUser = new QueryUser();
		String usertype = getPara("usertype");
		queryUser.setUsertype(usertype == null || usertype.equals("") ? 0
				: Integer.parseInt(usertype));
		queryUser.setEndtime(getPara("endtime"));
		queryUser.setStarttime(getPara("starttime"));
		queryUser.setUsername(getPara("username"));
		DataTablesModel userpage = userService.getPageUser(
				getParaToInt("userpages").intValue(), getParaToInt("userrp")
						.intValue(), queryUser);
		String[] heades = { "用户姓名", "用户登录名称", "用户邮箱", "用户电话", "用户类型", "是否启用" };
		ExportController.exportXLSRecord(userpage.getRows(), "用户管理", heades,
				"1", this.getResponse());
		logService.saveLog(EnumOptType.export.getEnumKey(), EnumFuncType.user.getEnumKey(), userService.getUser(getCurrentUserToken().getVuser().getUser())); //用户导出日志保存
		renderJson("导出成功");
	}
	 /**
	  * @author phm 重置密码
	  */
	 public void resetpassword(){
		 String id = getPara("id");
		 id = id==null?"":id;
		 try {
			 User user =User.dao.findById(id);
			 String password = new Sha256Hash("111111", user.getLoginName(), 1024).toBase64(); 
			 String sql = "update sys_user set password='"+password+"' where id='"+id+"'" ;
			 Db.update(sql);
			 String mark = getPara("message");
			 setAttr("message", mark);		
			 logService.saveLog(EnumOptType.reset.getEnumKey(), EnumFuncType.user.getEnumKey(), userService.getUser(getCurrentUserToken().getVuser().getUser())); //用户密码重置日志保存
			 renderJson("0");
		} catch (Exception e) {
			renderJson("1");
			e.printStackTrace();
		}
	 }
	 /**
	  * @author phm 验证用户名是否重复
	  */
	 @Clear
	 public void checkloginname(){
		 String loginName = getPara("loginName");
		 try {
			if(loginName!=null&&loginName.equals("")!=true){
				 String sql = "select * from sys_user where login_name = '"+loginName+"' and del_flag ='0' " ;
				 List<Record> userlist = new ArrayList<Record>();
				 userlist =  Db.find(sql);
				 if(userlist.size()>0){
					 renderJson("1");
				 }else {
					 renderJson("0");
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 /**
		 * @author cg
		 * 导入用户
		 * 
		 * */
		public void importUser(){
			setAttr("flagcg", getPara("flagcg"));
			render("userImport.jsp");
		}
	 
	 /**
	  * @author cg
	  * 用户下载模版
	  * */
		public void downloadExcel(){
			String path =UserController.class.getResource("").getPath()+"ImportUser.xls";
			 File file = new File(path);
			    if (file.isFile()) {
			        renderFile(file);
			        return;
			    }
		    // return;
		    renderNull();
			
		}
	 /**
	  * @author cg
	  * 用户导入
	  * */
		public void uploadExcel() {
		 	int flagcg=0;
		 	try{
		 		UploadFile file = this.getFile();
		        File source = file.getFile();
		        List<ImportUser> listuser=userService.checkExcel(source,getUser());
	    		if(listuser.size()>0){
	    			setAttr("listuser", listuser);
	    		}
		        flagcg =1;
		 	}catch(Exception ex){
		 		ex.printStackTrace();
		 	}
	        setAttr("flagcg", flagcg);
			render("userImport.jsp");
	    }
		
		public void getuserinfo(){
			String id = getPara("id");
			User user = new User();
			user = User.dao.findById(id);
			String yonghuleixing = Dict.dao.getDictByKeyType(user.getUserType()+"", "1003");
			List<Office> listoffice = new ArrayList<Office>();
			listoffice = Office.dao.find("select t.* from sys_office t ,sys_office_user t1 where t.id = t1.office_id and t1.user_id = ? ",id);
			String officenames="";
			if(listoffice.size()>0){
				for(Office o:listoffice){
					officenames =officenames +o.getName()+ " | ";
				}
			}
			setAttr("username", user.getName());
			setAttr("idcard", user.getCardno());
			setAttr("officenames", officenames);
			setAttr("yonghuleixing", yonghuleixing);
			renderJson();
		}
		
}