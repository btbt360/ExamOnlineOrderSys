package com.wide.baseproject.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.LogService;
import com.wide.baseproject.sys.service.RoleService;
import com.wide.baseproject.sys.service.UserService;
import com.wide.common.model.Dict;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.common.model.UserRole;
import com.wide.common.model.query.QueryRole;
import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.util.ExportController;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;

public class RoleController extends Controller {
	private static final UserService userService = Enhancer
			.enhance(UserService.class);
	private static final RoleService roleService = Enhancer
			.enhance(RoleService.class);
	private static final DictService dictService = Enhancer
			.enhance(DictService.class);
	private static final LogService logService = Enhancer
			.enhance(LogService.class);	
  

	public static UserService getUserservice() {
		return userService;
	}

	public static RoleService getRoleservice() {
		return roleService;
	}

	public static DictService getDictservice() {
		return dictService;
	}

	public static LogService getLogservice() {
		return logService;
	}	
	/**
	 * 获取当前用户
	 * @return User
	 */
	public User getCurrentUser(){
		HttpSession session = getSession();          //获取当前用户
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());
		
		return user;
		
	}
	/**
	 * @author cg 获取角色
	 * 
	 * */
	public void getMenuAll() {
		List<Role> listm = roleService.getRoleAll();
	}

	/**
	 * @author cg 角色管理
	 * */
	public void add() {
		List<Dict> listd = dictService.getDictByType("1001");
		setAttr("listdict", listd);
		render("roleList.jsp");
	}

	/**
	 * @author cg Datetables分页显示
	 * */
	public void addinfo() {
		QueryRole queryRole = new QueryRole();
		queryRole.setRolename(getPara("rolename"));
		queryRole.setRoletype(getPara("roletype"));
		queryRole.setStarttimes(getPara("starttimes"));
		queryRole.setEndtimes(getPara("endtimes"));
		DataTablesModel rolepage = roleService.getPageRole(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryRole);
		this.renderJson(rolepage);
	}

	/**
	 * @author cg 添加角色
	 * */
	public void addroleinfo() {
		String id = getPara("id");
		ViewRole vrole = new ViewRole();
		if (id != null && !id.equals("")) {
			vrole = roleService.getRoleByuId(id);
		}
		String mark = getPara("message");
		List<Dict> listd = dictService.getDictByType("1001");
		setAttr("listd", listd);
		setAttr("vrole", vrole);
		setAttr("vroletype", vrole.getRole() == null  ? 0
				: vrole.getRole().getRoleType() == null  ? 0
						: vrole.getRole().getRoleType());
		// setAttr("resids",vrole.getResids());
		// setAttr("rightids",vrole.getRightids());
		// setAttr("offids",vrole.getOffids());
		setAttr("message", mark);
		render("roleInfo.jsp");
	}

	/**
	 * @author cg 保存角色
	 * 
	 * */
	//@Before(RoleValidator.class)
	public void saveInfo() {
		Role role = getModel(Role.class);	
		String offids = getPara("offids");
		String resids = getPara("resids");
		String rightids = getPara("rightids");
		ViewRole viewrole = new ViewRole();	
		if (role != null && !role.equals("")) {
			viewrole.setRole(role);
			viewrole.setOffids(offids);
			viewrole.setResids(resids);
			viewrole.setRightids(rightids);
			if(role.getId() != null && !role.getId().equals("")){
				logService.saveLog(EnumOptType.edit.getEnumKey(), EnumFuncType.role.getEnumKey(), getCurrentUser()); //角色修改日志保存
			}else{
				logService.saveLog(EnumOptType.add.getEnumKey(), EnumFuncType.role.getEnumKey(), getCurrentUser()); //角色添加日志保存
			}
			roleService.saveInfo(viewrole, getCurrentUser());
			
		}
		redirect("/role/addroleinfo?message=success", true);
	}

	/**
	 * @author cg 删除角色
	 * */
	@Before(Tx.class)
	public boolean delroleinfo() {
		String id = getPara("id");		
		List<UserRole> list = userService.findByRoleId(id);
		if (list.size() > 0) {
			renderJson("1");
			return true;
		}
		renderJson("2");
		roleService.delroleinfo(id);
		logService.saveLog(EnumOptType.del.getEnumKey(), EnumFuncType.role.getEnumKey(), getCurrentUser()); //角色删除日志保存
		return true;
	}
	/**
	 * @author phm 导出角色
	 * */
	public void exportRole() {
		
		QueryRole queryRole = new QueryRole();
		String roletype = getPara("roletype");
		queryRole.setRoletype(roletype == null || roletype.equals("") ? null: roletype);
		queryRole.setStarttimes(getPara("starttimes"));
		queryRole.setEndtimes(getPara("endtimes"));
		queryRole.setRolename(getPara("rolename"));
		DataTablesModel rolepage = roleService.getPageRole(
				getParaToInt("rolepages").intValue(), getParaToInt("rolerp")
				.intValue(), queryRole);
		String[] heades = { "角色名称", "角色类型", "创建人", "创建时间", "拥有权限"};
		ExportController.exportXLSRecord(rolepage.getRows(), "角色管理", heades,
				"1", this.getResponse());
		logService.saveLog(EnumOptType.export.getEnumKey(), EnumFuncType.role.getEnumKey(), getCurrentUser()); //角色导出日志保存
		renderJson("导出成功");
	}
	 /**
	  * @author phm 验证用户名是否重复
	  */
	 @Clear
	 public void checkrolename(){
		 String rolename = getPara("rolename");
		 try {
			if(rolename!=null&&rolename.equals("")!=true){
				 String sql = "select * from sys_role where name = '"+rolename+"' and del_flag ='0' " ;
				 List<Record> rolelist = new ArrayList<Record>();
				 rolelist =  Db.find(sql);
				 if(rolelist.size()>0){
					 renderJson("1");
				 }else {
					 renderJson("0");
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
