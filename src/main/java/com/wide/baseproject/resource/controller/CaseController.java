package com.wide.baseproject.resource.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.resource.service.CaseService;
import com.wide.baseproject.resource.service.SubjectService;
import com.wide.common.model.Cases;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QueryCase;
import com.wide.common.model.query.QuerySubject;
import com.wide.viewmodel.DataTablesModel;

public class CaseController extends BaseController{
	
	private static final CaseService caseService = Enhancer.enhance(CaseService.class);
	private static final SubjectService subjectService = Enhancer.enhance(SubjectService.class);
	

	/**
	 * @author cg
	 * 进入试题类型
	 * */
	public void add(){
		QuerySubject querySubject = new QuerySubject();
		List<Subject> subjectlist = subjectService.getSubjecyListAll(querySubject);
		setAttr("subjectlist", subjectlist);
		render("caseList.jsp");
		
	}
	
	/**
	 * @author cg
	 * 查询试题类型
	 * */
	public void find(){
		QueryCase queryCase = new QueryCase();
		queryCase.setCasetitle(getPara("casetitle"));
		queryCase.setName(getPara("name"));
		DataTablesModel casepage = caseService.getPageCase(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryCase);
		this.renderJson(casepage);
		
	}
	
	/**
	 * @author cg
	 * 保存试题类型
	 * */
	public void addinfo(){
		String id = getPara("id");
		Cases cases = null;
		QuerySubject querySubject = new QuerySubject();
		List<Subject> subjectlist = subjectService.getSubjecyListAll(querySubject);
		Subject subject = new Subject();
		String subjectname = "";
		if(id!=null&&!id.equals("")){
			cases = Cases.dao.findById(id);
			subject = Subject.dao.findById(cases.getSubjectId());
			subjectname = subject.getName();
		}else{
			cases = new Cases();
		}
		setAttr("flagcg", getPara("flagcg"));
		setAttr("cases", cases);
		setAttr("subjectname", subjectname);
		setAttr("subjectlist", subjectlist);
		render("caseInfo.jsp");
		
	}
	
	/**
	 * @author cg
	 * 提交案例
	 * 
	 * */
	public void save(){
		int flagcg=0;
		try{
			Cases cases = getModel(Cases.class)==null||getModel(Cases.class).equals("")?new Cases():getModel(Cases.class);
			if(cases.getId()!=null&&!cases.getId().equals("")){
				cases.setUpdateBy(getUser().getId());
				cases.setUpdateDate(new Date());
				cases.update();
			}else{
				cases.setId(createUUid());
				cases.setCreatorId(getUser().getId());
				cases.setCreateDate(new Date());
				cases.setUpdateBy(getUser().getId());
				cases.setUpdateDate(new Date());
				cases.setIsdel(0);
				cases.save();
			}
			flagcg = 1;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		redirect("/case/addinfo?flagcg="+flagcg, true);
	}
	/**
	 * @author cg
	 * 删除试题类型
	 * */
	public void del(){
		returninfo = new RenturnInfo();
		String id = getPara("id");
		try{
			if(id!=null&&!id.equals("")){
				Db.update("update sys_cases set isdel = 1 where id = ? ",id);
			}
			returninfo.setResult(0);
			returninfo.setResultInfo("删除成功！");
		}catch(Exception ex){
			ex.printStackTrace();
			returninfo.setResult(1);
			returninfo.setResultInfo("删除失败！");
		}
		setAttr("returninfo", returninfo);
		renderJson();
	}
}
