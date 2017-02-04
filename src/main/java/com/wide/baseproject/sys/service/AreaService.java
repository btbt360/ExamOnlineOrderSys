package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.common.model.Area;
import com.wide.common.model.Dict;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryArea;
import com.wide.common.model.query.QueryUser;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;
import com.wide.viewmodel.ViewTree;
import com.jfinal.plugin.activerecord.Db;

public class AreaService
{
	/**
	 * 获取全部地区
	 */
	public static List<Area> getAreaAll()
	{
		List<Area> alist = new ArrayList<Area>();
		alist = Area.dao.getAreaAll();
		return alist;
	}

	/**
	 * 通过ID查找地区
	 */
	public Area getAreaById(String id)
	{
		Area area = Area.dao.findById(id);
		return area;
	}

	/**
	 * 删除地区 
	 */
	public void delarea(String id)
	{
		Area area = Area.dao.findById(id);
		area.setDelFlag("1");
		area.setCreateDate(new Date());
		area.setUpdateDate(new Date());
		area.update();
	}

	public List<ViewTree> getAreaTreeByPid(String id, String areaid)
	{
		List<Area> list = Area.dao.findByPid(id);
		List<ViewTree> vtlist = new ArrayList<ViewTree>();
		if (list.size() > 0)
		{
			for (Area a : list)
			{
				ViewTree vt = new ViewTree();
				List<Area> listchild = new ArrayList<Area>();
				vt.setId(a.getId());
				listchild = Area.dao.findByPid(a.getId());
				vt.setIsParent(listchild.size() > 0 ? true : false);
				vt.setIsHidden(false);
				vt.setChecked(areaid.equals(a.getId()));
				vt.setName(a.getName());
				vt.setParentTId(vt.getParentTId());
				vtlist.add(vt);
			}
		}
		return vtlist;
	}

	/**
	 * 根据query获取地区列表
	 */
	public static List<Area> getAreasByQuery(QueryArea queryArea)
	{
		List<Area> alist = new ArrayList<Area>();
		alist = Area.dao.getAreasByQuery(queryArea);
		return alist;
	}


	/**
	 * 找到排序的最大值
	 * 
	 * @author zb
	 * @param String
	 *            parentId 父级ID值
	 * @return String sort 本级别最大sort
	 */
	public String findMaxSort(String parentId)
	{
		// TODO Auto-generated method stub
		String sort = "";
		parentId = parentId == null ? "" : parentId;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select max(t.sort) from sys_area t where 1=1 and t.`parent_id` = '" + parentId
				+ "' group by t.`parent_id` "); // 通过parentId查找本级别最大的sort
		if (list.size() > 0)
		{
			sort = list.get(0) + "";
		}
		return sort;
	}

	/**
	 * 统计子节点
	 * 
	 * @author zb
	 * @param String
	 *            parentId 父级ID值
	 * @return String count 子节点数量
	 */
	public String countChild(String parentId)
	{
		String count = "";
		parentId = parentId == null ? "" : parentId;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select count(parent_id) from sys_area where parent_id='" + parentId + "' and del_flag='0'"); // 统计拥有该parent_id并且del_flag为0的数量
		if (list.size() > 0)
		{
			count = list.get(0) + "";
		}
		return count;
	}
}
