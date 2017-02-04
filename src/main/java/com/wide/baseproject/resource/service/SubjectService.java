package com.wide.baseproject.resource.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.wide.common.model.Dict;
import com.wide.common.model.Menu;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QuerySubject;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewRole;
import com.wide.viewmodel.ViewTree;

public class SubjectService {

	public DataTablesModel getPageSubject(int pageNum, int pageSize, QuerySubject querySubject) {
		// TODO Auto-generated method stub
		DataTablesModel subjectpage = Subject.dao.pageDataTables(pageNum, pageSize, querySubject);
		
		if (subjectpage != null && !subjectpage.equals("")) {
			List<List<String>> rows = subjectpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String info = row.get(3);
					row.set(3, Integer.parseInt((row.get(4) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					row.set(4, "<a href ='#' onclick=edit('" + row.get(0).trim()
							+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>"  );
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, info);
				}
			}
		}
		return subjectpage;
		
	}

	public List<Subject> getSubjecyListAll(QuerySubject querySubject) {
		// TODO Auto-generated method stub
		List<Subject> list = new ArrayList();
		list = Subject.dao.getSubjecyListAll(querySubject);
		return list;
	}

	public String findMaxSort(String parentid) {
		// TODO Auto-generated method stub
		String sort = "";
		parentid = parentid == null ? "" : parentid;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select max(t.sort) from sys_subject t where 1=1 and t.`parentid` = '" + parentid
				+ "' group by t.`parentid` ");
		if (list.size() > 0) {
			sort = list.get(0) + "";
		}
		return sort;
	}

	public String countChild(String id) {
		// TODO Auto-generated method stub
		String count = "";
		id = id == null ? "" : id;
		List<Object> list = new ArrayList<Object>();
		list = Db.query("select count(*) from sys_subject where parentid ='" + id + "' and isdel = 0 and isenable = 1 ");
		if (list.size() > 0) {
			count = list.get(0) + "";
		}
		return count;
	}

	public List<ViewTree> getSubjectTreeByPid(String id, String subjectid) {
		// TODO Auto-generated method stub
		List<Subject> list = Subject.dao.findByPid(id);
		List<ViewTree> vtlist = new ArrayList<ViewTree>();
		if (list.size() > 0) {
			for (Subject s : list) {
				ViewTree vt = new ViewTree();
				List<Subject> listchild = new ArrayList<Subject>();
				vt.setId(s.getId());
				listchild = Subject.dao.findByPid(s.getId());
				vt.setIsParent(listchild.size() > 0 ? true : false);
				// vt.setIconSkin(listchild.size()>0?"icon01":"icon02");
				vt.setIsHidden(false);
				vt.setChecked(subjectid.equals(s.getId()));
				vt.setName(s.getName());
				vt.setParentTId(vt.getParentTId());
				vtlist.add(vt);
			}
		}
		return vtlist;
	}



}
