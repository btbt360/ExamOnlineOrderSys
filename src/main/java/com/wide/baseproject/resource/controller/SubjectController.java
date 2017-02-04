package com.wide.baseproject.resource.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.resource.service.SubjectService;
import com.wide.common.model.Menu;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QuerySubject;
import com.wide.common.model.query.QueryUser;
import com.wide.util.CGUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewTree;

public class SubjectController extends BaseController{
	private static final SubjectService subjectService = Enhancer.enhance(SubjectService.class);
	
	/**
	 * @author cg
	 *  科目添加
	 * 
	 * */
	public void addsub(){
		List<Subject> subjectlist = new ArrayList<Subject>();
		QuerySubject querySubject = new QuerySubject();
		querySubject.setCode(getPara("code"));
		querySubject.setName(getPara("name"));
		subjectlist = subjectService.getSubjecyListAll(querySubject);
		setAttr("subjectlist", subjectlist);
		render("subjectList.jsp");
	}
	
	/**
	 * @author cg
	 * 科目列表
	 * */
	public void getsublist(){
		QuerySubject querySubject = new QuerySubject();
		querySubject.setCode(getPara("code"));
		querySubject.setName(getPara("name"));
		DataTablesModel subjectpage = subjectService.getPageSubject(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), querySubject);
		this.renderJson(subjectpage);
	}
	/**
	 * @author cg
	 * 科目添加详细
	 * 
	 * */
	public void addsubinfo(){
		String id = getPara("id");
		Subject subject,psubject = new Subject();
		subject = Subject.dao.findById(id == null ? "" : id);
		subject = subject == null || subject.equals("") ? new Subject() : subject;
		psubject = Subject.dao.findById(subject.getParentid() == null ? "" : subject.getParentid());
		setAttr("flagcg", getPara("flagcg"));
		setAttr("subject", subject);
		setAttr("psubject", psubject);
		setAttr("message",getPara("message"));
		render("subjectInfo.jsp");
	}
	/**
	 * @author cg
	 * 科目保存
	 * 
	 * */
	
	public void savesub(){
		int flagcg =0;
		try{
			Subject subject = getModel(Subject.class)==null||getModel(Subject.class).equals("")?new Subject():getModel(Subject.class);
			Subject subjectp = Subject.dao.findById(subject.getParentid());
			
			if (subjectp == null || subjectp.getSort().equals(""))
			{
				subjectp = new Subject();
			}
			if(StrKit.notBlank(subject.getId())){
				Subject subjectlast = Subject.dao.findById(subject.getId());
				if(!subjectlast.getParentid().equals(subject.getParentid())){
					String maxsort = subjectService.findMaxSort(subject.getParentid());
					subject.setSort(CGUtil.createSort(subjectp.getSort() == null || subjectp.getSort().equals("") ? 0.0 : subjectp.getSort(),
							Double.parseDouble(maxsort == null || maxsort.equals("") ? "0" : maxsort)));
				}
				subject.setUpdateBy(getUser().getId());
				subject.setUpdateDate(new Date());
				subject.update();
			}else{
				subject.setId(createUUid());
				subject.setParentid(subject.getParentid() == null ? "" : subject.getParentid());
				subject.setParentpath(subject.getParentid() == "" ? "" : (subject.getParentid() + "|" + subject.getId()));
				String maxsort = subjectService.findMaxSort(subject.getParentid());
				subject.setSort(CGUtil.createSort(subjectp.getSort() == null || subjectp.getSort().equals("") ? 0.0 : subjectp.getSort(),
						Double.parseDouble(maxsort == null || maxsort.equals("") ? "0" : maxsort)));
				subject.setCreatorId(getUser().getId());
				subject.setCreateDate(new Date());
				subject.setUpdateBy(getUser().getId());
				subject.setUpdateDate(new Date());
				subject.setIsdel(0);
				subject.save();
				
			}
			flagcg=1;
			setAttr("subject", subject);
			setAttr("flagcg", flagcg);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		redirect("/subject/addsubinfo?flagcg="+flagcg, true);
	}
	/**
	 * @author cg
	 * 科目删除
	 * */
	public void delsub(){
		String id = getPara("id");
		List<Subject> subjectlist = new ArrayList<Subject>();
		String num = subjectService.countChild(id);
		if (Integer.parseInt(num) == 0)
		{
			setAttr("message", "success");
			render("menuList.jsp");
			if(id!=null&&!id.equals("")){
				Db.update("update sys_subject set isdel = 1 where id = ? ",id);
			}
		} else
		{
			setAttr("message", "error");
			render("menuList.jsp");
		}
		QuerySubject querySubject = new QuerySubject();
		querySubject.setCode(getPara("code"));
		querySubject.setName(getPara("name"));
		subjectlist = subjectService.getSubjecyListAll(querySubject);
		setAttr("subjectlist", subjectlist);
		render("subjectList.jsp");
	}
	

	/**
	 * @author cg 查询科目树
	 */
	public void getSubjectTree()
	{
		String id = getPara("id");
		String subjectid = getPara("subjectid");
		id = id == null ? "" : id;
		List<ViewTree> list = subjectService.getSubjectTreeByPid(id, subjectid);
		renderJson(list);
	}
}
