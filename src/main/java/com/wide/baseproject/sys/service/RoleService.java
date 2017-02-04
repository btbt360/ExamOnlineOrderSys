package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wide.common.model.Dict;
import com.wide.common.model.Office;
import com.wide.common.model.Right;
import com.wide.common.model.Role;
import com.wide.common.model.RoleOffice;
import com.wide.common.model.RoleRight;
import com.wide.common.model.User;
import com.wide.common.model.UserRole;
import com.wide.common.model.query.QueryRole;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class RoleService {

	/**
	 * 按用户id查询角色map
	 * 
	 * @author cg
	 * @param id
	 * @return Map<String, String>
	 */

	public Map<String, String> getRoleByuserID(String id) {
		// TODO Auto-generated method stub
		Map<String, String> maps = new HashMap<String, String>();
		List<Role> lists = Role.dao.getRoleByuserID(id);
		if (lists.size() > 0) {
			for (Role o : lists) {
				maps.put(o.getId(), o.getName());
			}
		}
		return maps;
	}

	/**
	 * 按查询全部角色列表
	 * 
	 * @author cg
	 * @return List<Role>
	 */

	public List<Role> getRoleAll() {
		// TODO Auto-generated method stub
		List<Role> lists = Role.dao.getRollAll();
		return lists;
	}

	/**
	 * 按查询角色列表分页
	 * 
	 * @author cg
	 * @param pageNum页数
	 * @param pageSize每页条数
	 * @param queryRole查询实体对象
	 * @return DataTablesModel对象
	 */

	public DataTablesModel getPageRole(int pageNum, int pageSize, QueryRole queryRole) {
		DataTablesModel rolepage = Role.dao.pageDataTables(pageNum, pageSize, queryRole);
		if (rolepage != null && !rolepage.equals("")) {
			List<List<String>> rows = rolepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String valuestr = "";
					List<Right> rolerightlists = getRoleRightByroleId(row.get(0) + "");
					if (rolerightlists.size() > 0) {
						for (Right rr : rolerightlists) {
							valuestr = valuestr + "|" + rr.getResourcesname();
						}
					}
					User user = new User();
					if (row.get(2) != null && !row.get(2).equals("")) {
						user = User.dao.findById(row.get(3));
					}
					row.add(5, "<a href ='#' onclick=edit('" + row.get(0).trim()
							+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>");
					row.set(0, row.get(1));
					if (row.get(2) != null && !row.get(2).equals("")) {
						String dictvalue = Dict.dao.getDictByKeyType(row.get(2),"1001");
						row.set(1, dictvalue);
					} else {
						row.set(1, "无");
					}

					row.set(2, user.getName());
					row.set(3, row.get(4));
					row.set(4, valuestr);
				}
			}
		}

		return rolepage;
	}

	/**
	 * 按角色id查询角色权限
	 * 
	 * @author cg
	 * @param id
	 * @return List<Right>
	 */

	private List<Right> getRoleRightByroleId(String id) {
		// TODO Auto-generated method stub
		List<Right> list = new ArrayList<Right>();
		list = Right.dao.getRoleRightByroleId(id);
		return list;
	}

	/**
	 * 按用户id查询角色
	 * 
	 * @param id
	 * @return ViewRole对象
	 */
	public ViewRole getRoleByuId(String id) {
		// TODO Auto-generated method stub
		ViewRole vr = new ViewRole();
		Role role = new Role();
		List<Right> list = getRoleRightByroleId(id);
		List<Office> listOffice = Office.dao.findByRoleId(id);
		List<RoleRight> listRoleRight = RoleRight.dao.findByRoleId(id);
		role = Role.dao.findById(id);
		vr.setRole(role);
		vr.setRoleList(list);
		String resnames = "";
		String offnames = "";
		String rightids = "";
		String resids = "";
		String offids = "";
		if (list.size() > 0) {
			for (Right rr : list) {
				resnames = resnames + rr.getResourcesname() + "|";
				resids = resids + rr.getResourcesid() + "|";
			}
		}
		if (listOffice.size() > 0) {
			for (Office o : listOffice) {
				offnames = offnames + o.getName() + "|";
				offids = offids + o.getId() + "|";
			}
		}
		if (listRoleRight.size() > 0) {
			for (RoleRight rR : listRoleRight) {
				rightids = rightids + rR.getRightId() + "|";
			}
		}
		vr.setOffnames(offnames);
		vr.setResnames(resnames);
		vr.setRightids(rightids);
		vr.setResids(resids);
		vr.setOffids(offids);
		return vr;
	}

	/**
	 * 保存角色
	 * 
	 * @param vrole对象
	 * @param User对象
	 * @author cg
	 * 
	 */
	public void saveInfo(ViewRole vrole, User user) {
		// TODO Auto-generated method stub
		Role role = vrole.getRole() != null && !vrole.getRole().equals("") ? vrole.getRole() : new Role();
		List<String> rightlist = new ArrayList<String>();
		List<String> officelist = new ArrayList<String>();
		Db.query("set foreign_key_checks=0;");
		if (role.getId() != null && !role.getId().equals("")) {
			// 更新角色
			role.setUpdateBy(user.getId());
			role.setUpdateDate(new Date());
			role.update();
			if (vrole.getRightids() != null) {
				del(role.getId(), 1);
				rightlist = CGUtil.cutOffString(vrole.getRightids(), "|");
				saveroleright(rightlist, role.getId());
			}

			if (vrole.getOffids() != null) {
				del(role.getId(), 2);
				officelist = CGUtil.cutOffString(vrole.getOffids(), "|");
				saveroleoffice(officelist, role.getId());
			}

		} else {
			String id = CGUtil.createUUid();
			// 插入角色
			role.setId(id);
			role.setCreateBy(user.getId());
			role.setCreateDate(new Date());
			role.setDelFlag("0");
			role.setDataScope("1");
			role.setEnname("");
			role.setIsSys("0");
//			role.setUseable("1");
			role.setUpdateBy(user.getId());
			role.setUpdateDate(new Date());
			role.save();

			rightlist = CGUtil.cutOffString(vrole.getRightids(), "|");
			saveroleright(rightlist, id);
			officelist = CGUtil.cutOffString(vrole.getOffids(), "|");
			saveroleoffice(officelist, id);

		}

	}

	/**
	 * 保存角色权限对照表
	 * 
	 * @param rightlist
	 * @param roleid
	 */
	public void saveroleright(List<String> rightlist, String roleid) {
		if (rightlist.size() > 0) {
			for (String str : rightlist) {
				RoleRight rr = new RoleRight();
				rr.setRightId(str.trim());
				rr.setRoleId(roleid.trim());
				rr.save();
			}
		}
	}

	/**
	 * 保存角色机构对照表
	 * 
	 * @param officelist
	 * @param roleid
	 */
	public void saveroleoffice(List<String> officelist, String roleid) {
		if (officelist.size() > 0) {
			for (String str : officelist) {
				RoleOffice ro = new RoleOffice();
				ro.setOfficeId(str.trim());
				ro.setRoleId(roleid.trim());
				ro.save();
			}
		}
	}

	/**
	 * 删除对照表
	 * 
	 * @param roleid
	 * @param key
	 */
	public void del(String roleid, int key) {
		switch (key) {
		case 1:
			Db.update("delete from sys_role_right where role_id = ?", roleid);
			break;
		case 2:
			Db.update("delete from sys_role_office where role_id = ?", roleid);
			break;
		case 3:
			Db.update("delete from sys_user_role where role_id = ?", roleid);
			break;
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delroleinfo(String id) {
		// TODO Auto-generated method stub
		Db.query("set foreign_key_checks=0;");
		del(id, 1);
		del(id, 2);
		del(id, 3);
		Db.update("delete from sys_role where id = ? ", id);
		Db.query("set foreign_key_checks=1;");
	}
}
