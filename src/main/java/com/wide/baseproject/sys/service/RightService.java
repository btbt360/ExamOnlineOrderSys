package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wide.common.model.Menu;
import com.wide.common.model.Office;
import com.wide.common.model.Right;
import com.wide.common.model.Role;
import com.wide.common.model.RoleRight;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryRole;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;
import com.wide.viewmodel.ViewTree;
import com.jfinal.plugin.activerecord.Page;

public class RightService {

	/**
	 * 获取权限树
	 * @author cg
	 * @param id 树id
	 * @param roleid 上级角色id
	 * @return List<ViewTree>
	 * */
	public List<ViewTree> getRightTreeByPid(String id,String roleid) {
		// TODO Auto-generated method stub
				List<Object[]> list = Menu.dao.findByMenuPid(id);
				List<Right> rightlist = Right.dao.getRoleRightByroleId(roleid);
				String strs = getRightTree(rightlist);
				List<ViewTree> vtlist=new ArrayList<ViewTree>();
				if(list.size()>0){
					for(Object[] o:list){
						ViewTree vt = new ViewTree();
						List<Object[]> listchild = new ArrayList<Object[]>();
						vt.setId(o[0]+"");
						listchild = Menu.dao.findByMenuPid(o[0]+"");
						vt.setIsParent(listchild.size()>0?true:false);
						//vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
						vt.setChecked(strs.indexOf(o[3]+"")!=-1);
						vt.setResid(o[3]+"");
						vt.setIsHidden(false);
						vt.setName(o[2]+"");
						vt.setParentTId(o[1]+"");
						vtlist.add(vt);
					}
				}
				return vtlist;
	}
	/**
	 * 获取角色id字符串
	 * @author cg
	 * @param rightlist 角色列表
	 * @return String 
	 * */
	private String getRightTree(List<Right> rightlist){
		String strs = "";
		if(rightlist.size()>0){
			for(Right r:rightlist){
				strs = strs +"|"+r.getId();
			}
		}
		return strs;
	}

}
