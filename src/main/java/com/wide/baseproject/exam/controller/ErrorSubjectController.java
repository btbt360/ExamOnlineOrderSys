package com.wide.baseproject.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Enhancer;
import com.wide.base.BaseController;
import com.wide.baseproject.exam.service.ErrorSubjectService;
import com.wide.common.model.Dict;
import com.wide.common.model.query.QueryError;
import com.wide.common.model.query.QueryQuestion;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;
import com.wide.common.model.Error;
import com.wide.common.model.Exam;
import com.wide.common.model.Exercise;
import com.wide.common.model.Questions;

public class ErrorSubjectController extends BaseController {
	private static final ErrorSubjectService errorSubjectService = Enhancer.enhance(ErrorSubjectService.class);
	
	/**
	 * @author cg
	 * 进入我的错题
	 * 
	 * */
	public void adderror(){
		List<Dict> dictlist = new ArrayList<Dict>();
		dictlist = Dict.dao.getDictByType("1002");
		List<Exam> examlist= new ArrayList<Exam>();
		examlist = Exam.dao.find("select t1.* from sys_exam t1,sys_examinee t2 where t1.isdel = 0 and t1.isenable = 1 and t1.id= t2.exam_id and t2.user_id= ?",getUser().getId());
		setAttr("examlist", examlist);
		setAttr("dictlist", dictlist);
		render("myError.jsp");
	}
	/**
	 * @author cg
	 * 查询错题
	 * 
	 * */
	public void getErrorList(){
		QueryError qe = new QueryError();
		qe.setQuestiontype(getPara("questiontype"));
		qe.setUserid(getUser().getId());
		qe.setExamid(getPara("examid"));
		qe.setRestype(getPara("restype"));
		DataTablesModel errorpage = errorSubjectService.getPageError(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), qe);
		this.renderJson(errorpage);
	}
	/**
	 * @author cg
	 * 移除错题
	 * 
	 * */
	public void removenError(){
		String errorid = getPara("errorid");
		try{
			if(!TypeChecker.isEmpty(errorid)){
				Error.dao.deleteById(errorid);
			}
			setAttr("message", "移除成功！");
		}catch(Exception e){
			setAttr("message", "移除失败！");
			e.printStackTrace();
		}
		renderJson();
	}
	
	/**
	 * @author cg
	 * 错题练习
	 * 
	 * */
	public void errorpritics(){
		List<Error> listerror = new ArrayList<Error>();
		listerror = Error.dao.find("select * from sys_error where user_id = ? and isdel=0 and isenable = 1 ",getUser().getId());
		setAttr("count", listerror.size());
		render("myErrorMain.jsp");
	}
	/**
	 * @author cg
	 * 错题更新
	 * */
	public void getErrorUpdate(){
		String questionsid = getPara("questionsid");
		String userid = getUser().getId();
		List<Error> listerror = new ArrayList<Error>();
		try{
			if(!TypeChecker.isEmpty(questionsid)){
				listerror = Error.dao.find("select * from sys_error where user_id = ? and question_id = ? ",userid,questionsid);
				if(listerror.size()<=0){
					Error error = new Error();
					error.setId(createUUid());
					error.setQuestionId(questionsid);
					error.setRecourseId(error.getId());
					error.setRecourseType(2);
					error.setUserId(userid);
					error.setCreatorId(userid);
					error.setCreateDate(new Date());
					error.setUpdateBy(userid);
					error.setUpdateDate(new Date());
					error.setIsdel(0);
					error.setIsenable(1);
					error.save();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		renderJson();
	}
	/**
	 * @author cg
	 * 错题开始练习
	 * 
	 * */
	public void errorStart(){
		int sort=!TypeChecker.isEmpty(getPara("sort"))?getParaToInt("sort"):0;
		int flag = !TypeChecker.isEmpty(getPara("flag"))?getParaToInt("flag"):0;
		String username = getUser().getName();
		String userid =  getUser().getId();
		List<Questions> questionslist = new ArrayList<Questions>();
		Questions questions= new Questions();
		try{
			if(!TypeChecker.isEmpty(userid)){
					questionslist= errorSubjectService.findQuestionsByItSu(userid,sort,flag);
					if(questionslist.size()>0){
						questions=questionslist.get(0);
				}
			}
			if(TypeChecker.isEmpty(questions.getId())){
				questions.setTitle("<h1>错题练习已经结束!</h1>");
				setAttr("flag", 1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		setAttr("username", username);
		setAttr("questions", questions);
		setAttr("sort",sort+1);
		render("myErrorStart.jsp");
	}
}
