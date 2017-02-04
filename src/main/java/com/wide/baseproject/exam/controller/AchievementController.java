package com.wide.baseproject.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wide.base.BaseController;
import com.wide.baseproject.exam.service.AchievementService;
import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.ExamAnswer;
import com.wide.common.model.Examinee;
import com.wide.common.model.ExampapersQtypes;
import com.wide.common.model.query.QueryExaminee;
import com.wide.util.DoubleUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class AchievementController extends BaseController {
	private static final AchievementService achievementService = Enhancer.enhance(AchievementService.class);
	
	/**
	 * @author cg
	 * 判卷页面
	 * */
	public void add(){   
		List<Exam> examlist= new ArrayList<Exam>();
		examlist = Exam.dao.find("select * from sys_exam where isdel = 0 and isenable = 1 ");
		setAttr("examlist", examlist);
		render("judgeList.jsp");
	}
	/**
	 * @author cg
	 * 查询判卷
	 * */
	public void getJudgeList(){
		try{
			QueryExaminee queryExaminee = new QueryExaminee();
			queryExaminee.setExamId(getPara("examid"));
			queryExaminee.setExamineeId(getPara("examineeid"));
			queryExaminee.setStarttime(getPara("createtimes"));
			queryExaminee.setEndtime(getPara("createtimee"));
			DataTablesModel judgepage = achievementService.getPageExamineeJudge(getParaToInt("page")
					.intValue(), getParaToInt("rp").intValue(), queryExaminee);
			this.renderJson(judgepage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * @author cg
	 * 查询考生
	 * */
	public void getExaminee(){
		String examid = getPara("examid");
		try{
		List<Examinee> examineelist = new ArrayList<Examinee>();
		examineelist = Examinee.dao.find("select * from sys_examinee where isdel = 0 and isenable = 1 and exam_id = ? ",examid);
		renderJson(examineelist);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * @author cg
	 * 人工审核
	 * */
	public void addJudge(){
		String examineeid = getPara("examineeid");
		String examid = getPara("examid");
		try{
			List<Exam> examlist= new ArrayList<Exam>();
			examlist = Exam.dao.find("select * from sys_exam where isdel = 0 and isenable = 1 ");
			setAttr("examlist", examlist);
			setAttr("examinee", Examinee.dao.findById(examineeid));
			setAttr("examid", examid);
			render("judgeQuestion.jsp");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	
	/**
	 * @author cg
	 * 人工审核
	 * */
	public void addJudgeList(){
		QueryExaminee queryExaminee = new QueryExaminee();
		queryExaminee.setExamId(getPara("examid"));
		queryExaminee.setExamineeId(getPara("examineeid"));
		try{
			DataTablesModel judgepage = achievementService.getPageJudgeSalf(getParaToInt("page")
					.intValue(), getParaToInt("rp").intValue(), queryExaminee);
			this.renderJson(judgepage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * @author cg
	 * 审核通过
	 * */
	@Before(Tx.class)
	public void passJudgeList(){
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		String score = getPara("score");
		String questionsid = getPara("questionsid");
		try{
			//1.查询答案表
			ExamAnswer ea = new ExamAnswer();
			ea = ExamAnswer.dao.findFirst("select * from sys_exam_answer where exam_id = ? and examinee_id = ? and question_id = ? ",examid,examineeid,questionsid);
			//2.查询考生表
			Examinee examinee = new Examinee();
			examinee = Examinee.dao.findById(examineeid);
			//3.更新单个答案分数
			ea.setAnswerscores(Double.parseDouble(score));
			ea.setJudgetype(1);
			ea.setJudgepeopleid(getUser().getId());
			ea.setJudgepeoplename(getUser().getName());
			ea.setJudgetime(new Date());
			ea.setUpdateBy(getUser().getId());
			ea.setUpdateDate(new Date());
			ea.update();
			//4.查询整个ExamAnswerlist 更新考生总分
			List<ExamAnswer> ealist = new ArrayList<ExamAnswer>();
			ealist = ExamAnswer.dao.find("select * from sys_exam_answer where exam_id = ? and examinee_id = ? ",examid,examineeid);
			Double sumscores= 0.0;
			if(ealist.size()>0){
				for(ExamAnswer examAnswer:ealist){
					sumscores = DoubleUtil.add(sumscores, examAnswer.getAnswerscores());
				}
			}
			examinee.setTotalscore(sumscores);
			List<Dict> listdict = new ArrayList<Dict>();
			listdict = Dict.dao.getDictByType("1015");
			if(listdict.size()>0){
					if(Integer.parseInt(listdict.get(0).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(1).getDictkey())){
						examinee.setScoreslevel(0);
					}else if(Integer.parseInt(listdict.get(1).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(2).getDictkey())){
						examinee.setScoreslevel(1);
					}else if(Integer.parseInt(listdict.get(2).getDictkey())<sumscores){
						examinee.setScoreslevel(2);
					}
			}
			examinee.setUpdateBy(getUser().getId());
			examinee.setUpdateDate(new Date());
			examinee.update();
			setAttr("message", "考生试题复评通过！");
			renderJson();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	
	/**
	 * @author cg
	 * 一键通过
	 * */
	@Before(Tx.class)
	public void onekeypass(){
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		List<ExampapersQtypes> eqlist = new ArrayList<ExampapersQtypes>();
		eqlist = ExampapersQtypes.dao.find("select * from sys_exampapers_qtypes t,sys_exam t1 where t.exampapers_id=t1.exampapers_id and t1.id = ? ",examid);
		Double sumscores = 0.0;
		try{
			if(eqlist.size()>0){
				for(ExampapersQtypes eq:eqlist){
					if(eq.getTypeId()>3){
						setAttr("message", "此次考试试卷中，存在问答题和填空题，不能自动判分，请您手动判分！");
						renderJson();
						return;
					}
				}
			}
			
			List<ExamAnswer> ealist = new ArrayList<ExamAnswer>();
			ealist = ExamAnswer.dao.find("select * from sys_exam_answer where exam_id = ? and examinee_id = ? ",examid,examineeid);
			if(ealist.size()>0){
				for(ExamAnswer ea:ealist){
					ea.setJudgetype(1);
					ea.setJudgepeopleid(getUser().getId());
					ea.setJudgepeoplename(getUser().getName());
					ea.setJudgetime(new Date());
					ea.setUpdateBy(getUser().getId());
					ea.setUpdateDate(new Date());
					ea.update();
					sumscores = DoubleUtil.add(sumscores, ea.getAnswerscores());
				}
			}
			
			Examinee examinee = new Examinee();
			examinee = Examinee.dao.findById(examineeid);
			List<Dict> listdict = new ArrayList<Dict>();
			listdict = Dict.dao.getDictByType("1015");
			//sumscores = examinee.getTotalscore();
			if(listdict.size()>0){
					if(Integer.parseInt(listdict.get(0).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(1).getDictkey())){
						examinee.setScoreslevel(0);
					}else if(Integer.parseInt(listdict.get(1).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(2).getDictkey())){
						examinee.setScoreslevel(1);
					}else if(Integer.parseInt(listdict.get(2).getDictkey())<sumscores){
						examinee.setScoreslevel(2);
					}
			}
			examinee.setTotalscore(sumscores);
			examinee.setIsfinishjudge(1);
			examinee.setUpdateBy(getUser().getId());
			examinee.setUpdateDate(new Date());
			examinee.update();
			setAttr("message", "判卷完成！");
			renderJson();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * @author cg
	 * 判卷完成
	 * */
	@Before(Tx.class)
	public void passAlreadyJudge(){
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		Double sumscores = 0.0;
		try{
			List<ExamAnswer> ealist = new ArrayList<ExamAnswer>();
			ealist = ExamAnswer.dao.find("select * from sys_exam_answer where exam_id = ? and examinee_id = ? ",examid,examineeid);
			if(ealist.size()>0){
				for(ExamAnswer ea:ealist){
					ea.setJudgetype(1);
					ea.setJudgepeopleid(getUser().getId());
					ea.setJudgepeoplename(getUser().getName());
					ea.setJudgetime(new Date());
					ea.setUpdateBy(getUser().getId());
					ea.setUpdateDate(new Date());
					ea.update();
					sumscores = DoubleUtil.add(sumscores, ea.getAnswerscores());
				}
			}
			Examinee examinee = new Examinee();
			examinee = Examinee.dao.findById(examineeid);
			List<Dict> listdict = new ArrayList<Dict>();
			listdict = Dict.dao.getDictByType("1015");
			//Double sumscores = examinee.getTotalscore();
			if(listdict.size()>0){
					if(Integer.parseInt(listdict.get(0).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(1).getDictkey())){
						examinee.setScoreslevel(0);
					}else if(Integer.parseInt(listdict.get(1).getDictkey())<sumscores&&sumscores<=Integer.parseInt(listdict.get(2).getDictkey())){
						examinee.setScoreslevel(1);
					}else if(Integer.parseInt(listdict.get(2).getDictkey())<sumscores){
						examinee.setScoreslevel(2);
					}
			}
			examinee.setTotalscore(sumscores);
			examinee.setIsfinishjudge(1);
			examinee.setUpdateBy(getUser().getId());
			examinee.setUpdateDate(new Date());
			examinee.update();
			setAttr("message", "判卷完成！");
			renderJson();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}	
	
	public void addExamRecordList(){
		List<Exam> examlist= new ArrayList<Exam>();
		examlist = Exam.dao.find("select t1.* from sys_exam t1,sys_examinee t2 where t1.isdel = 0 and t1.isenable = 1 and t1.id= t2.exam_id and t2.user_id= ?",getUser().getId());
		setAttr("examlist", examlist);
		render("examineeResult.jsp");
	}
	/**
	 * @author cg
	 * 考生查看试卷和成绩
	 * */
	public void getExamRecordList(){
		Examinee e = new Examinee();
		e = Examinee.dao.findFirst("select * from sys_examinee where user_id = ? and exam_id = ? ",getUser().getId(),getPara("examid"));
		QueryExaminee queryExaminee = new QueryExaminee();
		queryExaminee.setExamId(getPara("examid"));
		queryExaminee.setUserid(getUser().getId());
		if(!TypeChecker.isEmpty(e)){
			queryExaminee.setExamineeId((!TypeChecker.isEmpty(e.getId()))?e.getId():"");			
		}
		try{
			DataTablesModel judgepage = achievementService.getPageExamRecord(getParaToInt("page")
					.intValue(), getParaToInt("rp").intValue(), queryExaminee);
			this.renderJson(judgepage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
