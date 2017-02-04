package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wide.common.model.Area;
import com.wide.common.model.Menu;
import com.wide.common.model.Office;
import com.wide.common.model.User;
import com.wide.viewmodel.ViewTree;
import com.jfinal.plugin.activerecord.Db;

public class OfficeService {

	/**
	 * 获取相关人员的组织机构，并排序
	 * @author cg
	 * @param userid用户编码
	 * @return Map<String, String>
	 */
	public Map<String, String> getOfficeByuserID(String userid) {
		Map<String, String> maps = new HashMap<String, String>();
		List<Office> lists = Office.dao.getOfficeByuserID(userid);
		if (lists.size() > 0) {
			for (Office o : lists) {
				maps.put(o.getId(), o.getName());
			}
		}
		return maps;
	}

	/**
	 * 获取相关人员的组织机构，并排序
	 * @author cg
	 * @param userid用户编码
	 * @return List<Office>
	 */
	public List<Office> getOfficeListByuserID(String userid) {
		List<Office> lists = new ArrayList<Office>();
		lists = Office.dao.getOfficeByuserID(userid);
		return lists;
	}
	/**
	 * 获取组织机构树根据编码
	 * @author cg
	 * @param id树编码
	 * @param ids上级树编码
	 * @param i采用哪种查询方式
	 * @return List<ViewTree>
	 * */
	public List<ViewTree> getOfficeTreeByPid(String id, String ids, int i) {
		// TODO Auto-generated method stub
		List<Office> list = Office.dao.findByPid(id);
		List<Office> lists = new ArrayList<Office>();
		switch (i) {
		case 0:
			lists = Office.dao.findOfficeById(ids);break;
		case 1:
			lists = Office.dao.findByRoleId(ids);break;
		case 2:
			lists = Office.dao.getOfficeByuserID(ids);break;
		}
		String strs = getSelectIds(lists);

		List<ViewTree> vtlist = new ArrayList<ViewTree>();
		if (list.size() > 0) {
			for (Office o : list) {
				ViewTree vt = new ViewTree();
				List<Office> listchild = new ArrayList<Office>();
				vt.setId(o.getId());
				listchild = Office.dao.findByPid(o.getId());
				vt.setIsParent(listchild.size() > 0 ? true : false);
				// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
				vt.setChecked(strs.indexOf(o.getId()) != -1);
				vt.setIsHidden(false);
				vt.setName(o.getName());
				vt.setParentTId(vt.getParentTId());
				vtlist.add(vt);
			}
		}
		return vtlist;
	}
	
	/**
	 * 获取组织机构树根据编码
	 * @author lubin
	 * @param id树编码
	 * @param ids上级树编码
	 * @param i采用哪种查询方式
	 * @return List<ViewTree>
	 * */
	public List<ViewTree> getUserTreeByPid(String id, String ids, String allids) {
		// TODO Auto-generated method stub
		List<Office> list = Office.dao.findByPid(id);
		String strs = "";
		if(allids != null)
		   strs = allids;
		
		List<User> listUser = new ArrayList<User>();
		List<ViewTree> vtlist = new ArrayList<ViewTree>();
		listUser = User.dao.getUserByOfficeId(id);
		if(listUser.size() >0){
			for(User u : listUser){
				ViewTree vt = new ViewTree();
				vt.setId(u.getId());
				vt.setIsParent(false);
				// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
				vt.setChecked(strs.indexOf(u.getId()) != -1);
				vt.setIsHidden(false);
				vt.setName(u.getName());
				vt.setParentTId(id);
				vt.setType("1");
				vtlist.add(vt);
			}
	    }
		if (list.size() > 0) {
			for (Office o : list) {
				ViewTree vt = new ViewTree();
				List<Office> listchild = new ArrayList<Office>();
				listchild = Office.dao.findByPid(o.getId());
				if(listchild.size() > 0){
					vt.setId(o.getId());
					vt.setIsParent(true);
					// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
					vt.setChecked(strs.indexOf(o.getId()) != -1);
					vt.setIsHidden(false);
					vt.setName(o.getName());
					vt.setParentTId(vt.getParentTId());
					vt.setType("0");
					vtlist.add(vt);
				}else{
//					office = Office.dao.getOfficeById(o.getId());
					listUser = User.dao.getUserByOfficeId(o.getId());
					if(listUser.size() >0){
				    	vt.setId(o.getId());
						vt.setIsParent(true);
						// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
						vt.setChecked(strs.indexOf(o.getId()) != -1);
						vt.setIsHidden(false);
						vt.setName(o.getName());
						vt.setParentTId(vt.getParentTId());
						vt.setType("0");
						vtlist.add(vt);
					}else{
						vt.setId(o.getId());
						vt.setIsParent(false);
						// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
						vt.setChecked(strs.indexOf(o.getId()) != -1);
						vt.setIsHidden(false);
						vt.setName(o.getName());
						vt.setParentTId(vt.getParentTId());
						vt.setType("0");
						vtlist.add(vt);
					}
				}
			}
		}
		return vtlist;
	}
	
	
	
	/**
	 * 根据角色列表返回角色ID串
	 * @author cg
	 * @param List<Office> listrole
	 * @return String
	 */
	private String getSelectIds(List<Office> listrole) {
		String strs = "";
		if (listrole.size() > 0) {
			for (Office o : listrole) {
				strs = strs + "|" + o.getId();
			}
		}
		return strs;
	}
	/**
	 * 查询所有机构
	 * @author cg
	 * @return List<Office>
	 */
	public List<Office> getOfficeAll() {
		// TODO Auto-generated method stub
		return Office.dao.getOfficeAll();
	}
	/**
	 * 根据机构Id查询机构对象
	 * @author cg
	 * @return Office
	 */
	public Office getOfficeByid(String id) {
		// TODO Auto-generated method stub
		return Office.dao.findById(id);
	}
	/**
	 * 根据上级机构Id查询机构对象
	 * @author cg
	 * @return Office
	 */
	public Office getOfficeByPid(String parentId) {
		// TODO Auto-generated method stub
		List<Office> list = new ArrayList<Office>();
		list = Office.dao.findByPid(parentId);
		Office o =new Office();
		if(list.size()>0){
			o=list.get(0);
		}
		return o;
	}
	/**
	 * 根据上级机构Id查询下级机构最大排序
	 * @author cg
	 * @return String
	 */
	public String findMaxSort(String parentId) {
		// TODO Auto-generated method stub
		 String sort="";
		 parentId =parentId==null?"":parentId;
		 List<Object> list = new ArrayList<Object>();
		 list = Db.query("select max(t.sort) from sys_office t where 1=1 and t.`parent_id` = '"+parentId+"' group by t.`parent_id` ");
		 if(list.size()>0){
			 sort = list.get(0)+"";
		 }
		 return sort;
	}
	/**
	 * 查询组织机构中的地区树
	 * @author cg
	 * @param id地区ID
	 * @param areaid 上级地区id
	 * @param office 对应的组织机构ID
	 * @return List<ViewTree>
	 */
	public List<ViewTree> getAreaTreeByPid(String id,String areaid,Office office)
	{
		office = office==null||office.equals("")?new Office():office;
		office.setAreaId(office.getAreaId()==null||office.getAreaId().equals("")?"":office.getAreaId());
		List<Area> list = new ArrayList<Area>();
		Area pArea =  Area.dao.findById(areaid);
		pArea = pArea==null||pArea.equals("")?new Area():pArea;
		if(pArea.getParentId()!=null&&office.getParentId()!=null&&!office.getParentId().equals("")){
			id = id.equals("")?pArea.getParentId():id;			
		}
		list = Area.dao.findByPid(id);
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
				vt.setChecked(office.getAreaId().equals(a.getId()));
				vt.setName(a.getName());
				vt.setParentTId(vt.getParentTId());
				vtlist.add(vt);
			}
		}
		return vtlist;
	}


}
