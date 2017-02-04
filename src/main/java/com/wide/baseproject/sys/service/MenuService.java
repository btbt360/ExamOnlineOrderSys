package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.common.model.Menu;
import com.wide.common.model.Right;
import com.wide.viewmodel.ViewTree;
import com.jfinal.plugin.activerecord.Db;

public class MenuService {

	/**
	 * 获取全部菜单
	 * 
	 * @author cg
	 * @return List<Menu>
	 */
	public List<Menu> getMenuAll() {
		List<Menu> mlist = new ArrayList<Menu>();
		mlist = Menu.dao.getMenuAll();
		return mlist;
	}

	/**
	 * 通过ID获取Menu对象
	 * 
	 * @author cg
	 * @param id 菜单id
	 * @return Menu对象
	 */
	public Menu getMenuById(String id) {
		// TODO Auto-generated method stub
		Menu menu = Menu.dao.findById(id);
		return menu;
	}

	/**
	 * 通过ID删除Menu对象
	 * 
	 * @author cg
	 * @param id Menuid
	 */
	public void delmeun(String id) {
		// TODO Auto-generated method stub
		Menu menu = Menu.dao.findById(id);
		menu.setDelFlag("1");
		menu.setCreateDate(new Date());
		menu.setUpdateDate(new Date());
		menu.update();
	}

	/**
	 * 获取Menu树
	 * 
	 * @author cg
	 * @param id 返回的树id
	 * @param menuid选择菜单id
	 * @return List<ViewTree>
	 */
	public List<ViewTree> getMenuTreeByPid(String id, String menuid) {
		// TODO Auto-generated method stub
		List<Menu> list = Menu.dao.findByPid(id);
		List<ViewTree> vtlist = new ArrayList<ViewTree>();
		if (list.size() > 0) {
			for (Menu m : list) {
				ViewTree vt = new ViewTree();
				List<Menu> listchild = new ArrayList<Menu>();
				vt.setId(m.getId());
				listchild = Menu.dao.findByPid(m.getId());
				vt.setIsParent(listchild.size() > 0 ? true : false);
				// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
				vt.setIsHidden(false);
				vt.setChecked(menuid.equals(m.getId()));
				vt.setName(m.getName());
				vt.setParentTId(vt.getParentTId());
				vtlist.add(vt);
			}
		}
		return vtlist;
	}

	/**
	 * 根据用户Id查询菜单
	 * 
	 * @author cg
	 * @param id 返回的树id
	 * @param menuid选择菜单id
	 * @return List<ViewTree>
	 */
	public List<Menu> getMenuByuserID(String id) {
		// TODO Auto-generated method stub
		List<Menu> menulist = new ArrayList<Menu>();
		menulist = Menu.dao.find(
				"select DISTINCT t5.* from `sys_user_role` t1,`sys_role` t2,`sys_role_right` t3,`sys_right` t4,`sys_menu` t5 "
						+ " where t1.`role_id` = t2.id and t2.id = t3.`role_id` and t3.`right_id` = t4.id and t4.`resourcesid` = t5.id and t1.`user_id` = ? order by t5.sort asc",
				id);
		return menulist;
	}

	/**
	 * 查询最大排序
	 * 
	 * @author cg
	 * @param parentId 父级菜单ID
	 * @return String
	 */
	public String findMaxSort(String parentId) {
		// TODO Auto-generated method stub
		String sort = "";
		parentId = parentId == null ? "" : parentId;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select max(t.sort) from sys_menu t where 1=1 and t.`parent_id` = '" + parentId
				+ "' group by t.`parent_id` ");
		if (list.size() > 0) {
			sort = list.get(0) + "";
		}
		return sort;
	}

	/**
	 * 查询子菜单数
	 * 
	 * @author eh
	 * @param parentId 父级菜单ID
	 * @return String
	 */
	public String countChild(String parentId) {
		String count = "";
		parentId = parentId == null ? "" : parentId;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select count(*) from sys_menu where parent_id='" + parentId + "' and del_flag='0'");
		if (list.size() > 0) {
			count = list.get(0) + "";
		}
		return count;
	}

	/**
	 * 根据菜单ID查询权限
	 * 
	 * @author eh
	 * @param id 菜单ID
	 * @return Right对象
	 */
	public Right getRightBymenuId(String id) {
		Right right = Right.dao.getRightBymenuId(id);
		return right;
	}

}
