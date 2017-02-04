package com.wide.baseproject.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wide.baseproject.sys.service.AreaService;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.LogService;
import com.wide.baseproject.sys.service.OfficeService;
import com.wide.baseproject.sys.service.RoleService;
import com.wide.baseproject.sys.service.UserService;
import com.wide.common.model.Area;
import com.wide.common.model.Office;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.util.CGUtil;
import com.wide.viewmodel.ViewTree;

public class OfficeController extends Controller {
	private static final OfficeService officeService = Enhancer
			.enhance(OfficeService.class);
	private static final RoleService roleService = Enhancer
			.enhance(RoleService.class);
	private static final DictService dictService = Enhancer
			.enhance(DictService.class);
	private static final UserService userService = Enhancer
			.enhance(UserService.class);
	private static final AreaService areaService = Enhancer
			.enhance(AreaService.class);
	private static final LogService logService = Enhancer
			.enhance(LogService.class);

	public static OfficeService getOfficeservice() {
		return officeService;
	}

	public static RoleService getRoleservice() {
		return roleService;
	}

	public static DictService getDictservice() {
		return dictService;
	}

	public static UserService getUserservice() {
		return userService;
	}

	public static AreaService getAreaservice() {
		return areaService;
	}

	public static LogService getLogservice() {
		return logService;
	}

	/**
	 * 获取当前用户
	 * 
	 * @return User
	 */
	public User getCurrentUser() {
		HttpSession session = getSession(); // 获取当前用户
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());

		return user;

	}

	/**
	 * @author cg 获取菜单
	 * 
	 * */
	public void getMenuAll() {
		List<Role> listm = roleService.getRoleAll();
	}

	/**
	 * @author cg 机构管理
	 * */
	public void add() {

		List<Office> officelist = new ArrayList<Office>();
		officelist = officeService.getOfficeAll();
		if (officelist.size() > 0) {
			for (Office o : officelist) {
				String typename = dictService.getDictByKeyType(o.getType(),
						"1000");
				o.setFax(typename);
			}
		}
		String message = getPara("message");
		setAttr("message", message);
		setAttr("officelist", officelist);
		render("officeList.jsp");
	}

	/**
	 * @author cg 添加组织机构
	 * */
	public void addofficeinfo() {
		String id = getPara("id");
		String pid = getPara("pid");
		String type = getPara("type");
		Office office = new Office();
		Office parentOffice = new Office();
		Office poffice = new Office();
		Area area = new Area();
		if (id != null && !id.equals("")) {
			office = officeService.getOfficeByid(id);
			parentOffice = officeService.getOfficeByid(office.getParentId());
			if (parentOffice == null || parentOffice.equals("")) {
				parentOffice = new Office();
				parentOffice.setName("");
			}
			area = areaService.getAreaById(office.getAreaId());
		}
		if (pid != null && !pid.equals("")) {
			poffice = officeService.getOfficeByid(pid);
			area = areaService.getAreaById(poffice.getAreaId());
		}
		String mark = getPara("message");
		String typename = dictService.getDictByKeyType(type, "1000");
		String lastpname = poffice.getName() != null
				&& !poffice.getName().equals("") ? poffice.getName()
				: parentOffice.getName();
		String areaname = area == null ? "" : area.getName() == null ? ""
				: area.getName();
		String areaid = area == null ? "" : area.getId() == null ? "" : area
				.getId();
		setAttr("office", office);
		setAttr("type", type);// 新建下级机构、部门、岗位类型
		setAttr("typename", typename);
		setAttr("lastpname", lastpname);
		setAttr("areaname", areaname);
		setAttr("areaid", areaid);
		setAttr("parentId", pid);
		setAttr("message", mark);
		render("officeInfo.jsp");
	}

	/**
	 * @author cg 保存office
	 * */
	// @Before(OfficeValidator.class)
	public void saveofficeinfo() {

		Office office = getModel(Office.class);
		office.setUpdateBy(getCurrentUser().getId());
		office.setUpdateDate(new Date());
		Office oldoffice = officeService.getOfficeByid(office.getParentId());
		Office lastOffice = new Office();
		if (oldoffice == null) {
			oldoffice = new Office();
		}
		Db.query("set foreign_key_checks=0;");
		office.setAreaId(office.getAreaId() == null ? "" : office.getAreaId());
		office.setParentId(office.getParentId() == null ? "" : office
				.getParentId());
		if (StrKit.notBlank(office.getId())) {
			lastOffice = officeService.getOfficeByid(office.getId());
			if(!lastOffice.getParentId().equals(office.getParentId())){
				String maxsort = officeService.findMaxSort(office.getParentId());
				office.setSort(CGUtil.createSort(
						oldoffice.getSort() == null
								|| oldoffice.getSort().equals("") ? 0.0 : oldoffice
								.getSort(), Double.parseDouble(maxsort == null
								|| maxsort.equals("") ? "0" : maxsort)));
			}
			office.update();
			logService.saveLog(EnumOptType.edit.getEnumKey(),
					EnumFuncType.office.getEnumKey(), getCurrentUser()); // 机构修改日志保存
		} else {
			office.setId(CGUtil.createUUid());
			office.setParentIds((office.getParentId() == null ? "" : office
					.getParentId()) + "|" + office.getId());
			String maxsort = officeService.findMaxSort(office.getParentId());
			office.setSort(CGUtil.createSort(
					oldoffice.getSort() == null
							|| oldoffice.getSort().equals("") ? 0.0 : oldoffice
							.getSort(), Double.parseDouble(maxsort == null
							|| maxsort.equals("") ? "0" : maxsort)));
			office.setGrade("0");
			office.setCreateBy(getCurrentUser().getId());
			office.setCreateDate(new Date());
			office.save();
			logService.saveLog(EnumOptType.add.getEnumKey(),
					EnumFuncType.office.getEnumKey(), getCurrentUser()); // 机构添加日志保存
		}
		Db.query("set foreign_key_checks=1;");
		//redirect("/office/addofficeinfo?message=success", true);
		redirect("/office/add?message=success", true);
		
	}

	/**
	 * @author cg 删除菜单
	 * */
	public void delofficeinfo() {
		String id = getPara("id");
		Office office = new Office();
		int flag = 0;
		if (id != null && !id.equals("")) {
			office = officeService.getOfficeByPid(id);
		}
		if (office != null && office.getId() != null
				&& !office.getId().equals("")) {
			flag = 1;
			setAttr("message", "error");
			redirect("/office/add?message=error", true);
		} else {
			flag = 2;
			setAttr("message", "success");
			Db.update("update sys_office set del_flag = 1 where id = ? ", id);
			logService.saveLog(EnumOptType.del.getEnumKey(),
					EnumFuncType.office.getEnumKey(), getCurrentUser()); // 机构删除日志保存
			redirect("/office/add?message=success", true);
		}

	}

	/**
	 * @author cg 查询菜单树
	 * */
	public void getOfficeTree() {
		String id = getPara("id");
		String ids = getPara("ids");
		String roleid = getPara("roleid");
		String userid = getPara("userid");
		List<ViewTree> list = new ArrayList<ViewTree>();
		id = id == null ? "" : id;
		if (roleid != null && !roleid.equals("")) {
			roleid = roleid == null ? "" : roleid;
			list = officeService.getOfficeTreeByPid(id, roleid, 1);
		} else if (userid != null && !userid.equals("")) {
			userid = userid == null ? "" : userid;
			list = officeService.getOfficeTreeByPid(id, userid, 2);
		} else {
			list = officeService.getOfficeTreeByPid(id, ids, 0);
		}
		renderJson(list);
	}
	
	
	/**
	 * @author lubin
	 *  查询菜单树
	 * */
	public void getUserTree() {
		String id = getPara("id");
		String roleid = getPara("roleid");
		String userid = getPara("userid");
		String allids = getPara("userids");
		List<ViewTree> list = new ArrayList<ViewTree>();
		id = id == null ? "" : id;
	
		list = officeService.getUserTreeByPid(id, roleid, allids);

		renderJson(list);
	}

	/**
	 * @author phm 查询菜单树
	 */
	public void getAreaTree() {
		String id = getPara("id");
		String areaid = getPara("areaid");
		String officeid = getPara("officeid");
		Office office = new Office();
		id = id == null ? "" : id;
		if (officeid != null && !officeid.equals("")) {
			office = officeService.getOfficeByid(officeid);
		}
		List<ViewTree> list = officeService
				.getAreaTreeByPid(id, areaid, office);
		renderJson(list);
	}

	public void getArealist() {
		List<Office> officelist = new ArrayList<Office>();
		officelist = officeService.getOfficeAll();
		if (officelist.size() > 0) {
			for (Office o : officelist) {
				String typename = dictService.getDictByKeyType(o.getType(),
						"1000");
				o.setFax(typename);
			}
		}

		renderJson(officelist);
	}

	/***
	 * 机构编码重复验证
	 */
	@Clear
	public void checkoffice() {
		String code = "0";
		String officecode = getPara("officecode");
		try {
			if (officecode.equals("") != true) {
				List<Record> officelist = new ArrayList<Record>();
				String sql = "select * from sys_office where code='"
						+ officecode + "' and del_flag ='0' ";
				officelist = Db.find(sql);
				if (officelist.size() > 0) {
					code = "1";
				}
			}
			renderJson(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
