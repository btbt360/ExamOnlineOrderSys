package com.wide.baseproject.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.exam.service.AchievementService;
import com.wide.baseproject.exam.service.ExamineeService;
import com.wide.baseproject.exam.service.InvigilateService;
import com.wide.common.model.Exam;
import com.wide.common.model.ExamAnswer;
import com.wide.common.model.Examinee;
import com.wide.common.model.ExampapersQuestion;
import com.wide.common.model.Questions;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExam;
import com.wide.util.DoubleUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ExamineeController extends BaseController {
	private static final InvigilateService invigilateService = Enhancer.enhance(InvigilateService.class);
	private static final ExamineeService examineeService = Enhancer.enhance(ExamineeService.class);
	private static final AchievementService achievementService = Enhancer.enhance(AchievementService.class);
	

	/**
	 * @author cg 参加考试
	 * 
	 */
	public void addJoinExam() {
		render("joinExamList.jsp");
	}

	/**
	 * @author cg 考试列表 考生 0
	 */
	public void getJoinExamlist() {
		QueryExam queryExam = new QueryExam();
		queryExam.setName(getPara("name"));
		queryExam.setStarttimes(getPara("starttimes"));
		queryExam.setEndtimes(getPara("endtimes"));
		queryExam.setUserid(getUser().getId());
		DataTablesModel invigilatepage = invigilateService.getPageInvigilate(getParaToInt("page").intValue(),
				getParaToInt("rp").intValue(), queryExam, 0);
		this.renderJson(invigilatepage);
	}

	/**
	 * @author cg 进入考试
	 */
	public void addStartExam() {
		Examinee examinee = null;
		Exam exam = new Exam();
		int quflag = 0;// 开启试题 1开启 0未开启
		int anflag = 0;// 开启答案 1开启 0未开启
		try {
			String examid = getPara("id");
			// 1.查询考试
			exam = Exam.dao.findById(examid);
			List<Examinee> elist = new ArrayList<Examinee>();
			User user = getUser();
			elist = Examinee.dao.find(
					"select * from sys_examinee where user_id = '" + user.getId() + "' and exam_id ='" + examid + "'");
			// 2.查询考生
			examinee = elist.size() > 0 ? elist.get(0) : new Examinee();
			// 3.查询考题
			quflag = 1;
			// 4.查询答案
			anflag = 0;
			setAttr("quflag", quflag);
			setAttr("anflag", anflag);
			setAttr("exam", exam);
			setAttr("examinee", examinee);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		render("startExam.jsp");
	}

	/**
	 * @author cg 查询考题
	 */
	public void getQuestions() {
		String exampapersid = getPara("exampapersid");
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		int sorts = getParaToInt("sorts");
		String qustrhead = "";
		String qustrbody = "";
		String qustrfoot = "";
		try {
			qustrhead = "<div class='block' style='width:95%;margin-left: 2.5%;'><div class='navbar navbar-inner block-header'><div class='muted pull-left'>试题</div></div><div class='block-content collapse in'><div class='span12'><fieldset>";
			qustrbody = examineeService.getQuestions(exampapersid, examid, examineeid, sorts);
			qustrfoot = "</fieldset></div></div></div>";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setAttr("questionstr", qustrhead + qustrbody + qustrfoot);
		renderJson();
	}

	/**
	 * @author cg 查询答案
	 */
	public void getAnswers() {
		String exampapersid = getPara("exampapersid");
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		String eqstr = "<div class='block' style='width:95%;margin-left: 2.5%;'><div class='navbar navbar-inner block-header'><div class='muted pull-left'>选题</div></div><div class='block-content collapse in'><div class='span12'>";
		try {
			List<ExampapersQuestion> listeq = new ArrayList<ExampapersQuestion>();
			listeq = ExampapersQuestion.dao.find(
					"select * from sys_exampapers_question where exampapers_id = ? order by sort asc ", exampapersid);
			if (listeq.size() > 0) {
				for (int i = 0; i < listeq.size(); i++) {
					List<ExamAnswer> listea = new ArrayList<ExamAnswer>();
					listea = ExamAnswer.dao.find(
							"select * from sys_exam_answer where examinee_id = ? and exam_id = ? and question_id = ? ",
							examineeid, examid, listeq.get(i).getQuestionId());
					Questions question = Questions.dao.findById(listeq.get(i).getQuestionId());
					String answerTypestr = "answeroption_";
					if (StrKit.notNull(question)) {
						if (question.getQuestiontype() == 4 || question.getQuestiontype() == 5) {
							answerTypestr = "answerwd";
						}
					}
					if (listea.size() > 0) {
						eqstr = eqstr
								+ "<button type='button' class='btn btn-info' style='margin:3px;' onclick=updateanswer('"
								+ listeq.get(i).getQuestionId() + "','" + listeq.get(i).getSort() + "','"
								+ answerTypestr + "')>" + listeq.get(i).getSort() + "</button>";
					} else {
						eqstr = eqstr
								+ "<button type='button' class='btn btn-defaul' style='margin:3px;' onclick=updateanswer('"
								+ listeq.get(i).getQuestionId() + "','" + listeq.get(i).getSort() + "','"
								+ answerTypestr + "')>" + listeq.get(i).getSort() + "</button>";
					}
					if (i > 0 && i % 15 == 0) {
						eqstr = eqstr + "<br />";
					}
				}
			}
			eqstr = eqstr + "</div></div></div>";
			setAttr("eqstr", eqstr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		renderJson();
	}

	/**
	 * @author cg 新建答案
	 */
	@Before(Tx.class)
	public void getUpdateQuestionAnswer() {
		String exampapersid = getPara("exampapersid");
		String examid = getPara("examid");
		String examineeid = getPara("examineeid");
		String sort = getPara("sort");
		String answer = getPara("answer");
		String questionid = getPara("questionid");
		List<ExamAnswer> ealist = new ArrayList<ExamAnswer>();
		ExamAnswer ea = new ExamAnswer();
		try {
			// 下机
			Examinee examinee = new Examinee();
			if (!TypeChecker.isEmpty(examineeid)) {
				examinee = Examinee.dao.findById(examineeid);
				if (examinee.getStatus() == 4) {
					setAttr("flag", 1);
					renderJson();
					return;
				}
			}
			Questions questions = Questions.dao.findById(questionid);
			Double sumsocres = 0.0;
			// 1.添加答案
			if (StrKit.notBlank(answer)) {
				if (!TypeChecker.isEmpty(questionid) && !TypeChecker.isEmpty(examineeid)
						&& !TypeChecker.isEmpty(examid)) {
					ealist = ExamAnswer.dao.find(
							"select * from sys_exam_answer where examinee_id = ? and exam_id = ? and question_id = ? ",
							examineeid, examid, questionid);
				if (!TypeChecker.isEmpty(questions)) {
					if (ealist.size() > 0){ 
						ea = ealist.get(0);
							if (answer.equals(questions.getQuestionanswer())){ 
								ExampapersQuestion exampapersQuestion = ExampapersQuestion.dao
										.getExampapersQuestion(questionid, exampapersid);
								if (!TypeChecker.isEmpty(exampapersQuestion)) {
									ea.setAnswerscores(exampapersQuestion.getScores());
									ea.setAnswerinfo(answer);
									ea.setUpdateBy(getUser().getId());
									ea.setUpdateDate(new Date());
									ea.update();
								}
									
							}else{
								ea.setAnswerinfo(answer);
								ea.setUpdateBy(getUser().getId());
								ea.setUpdateDate(new Date());
								ea.update();
								invigilateService.saveError(questionid,answer,getUser().getId(),examid);
							}
						}else{
							if (answer.equals(questions.getQuestionanswer())){ 
								ExampapersQuestion exampapersQuestion = ExampapersQuestion.dao
										.getExampapersQuestion(questionid, exampapersid);
								if (!TypeChecker.isEmpty(exampapersQuestion)) {
									ea.setId(createUUid());
									ea.setExamineeId(examineeid);
									ea.setExamId(examid);
									ea.setQuestionId(questionid);
									ea.setAnswerscores(exampapersQuestion.getScores());
									ea.setAnswerinfo(answer);
									ea.setCreatorId(getUser().getId());
									ea.setCreateDate(new Date());
									ea.setUpdateBy(getUser().getId());
									ea.setUpdateDate(new Date());
									ea.setIsenable(1);
									ea.setJudgetime(new Date());
									ea.save();
								}
						}else{
							ea.setId(createUUid());
							ea.setExamineeId(examineeid);
							ea.setExamId(examid);
							ea.setQuestionId(questionid);
							ea.setAnswerinfo(answer);
							ea.setCreatorId(getUser().getId());
							ea.setCreateDate(new Date());
							ea.setUpdateBy(getUser().getId());
							ea.setUpdateDate(new Date());
							ea.setIsenable(1);
							ea.setJudgetime(new Date());
							ea.save();
							invigilateService.saveError(questionid,answer,getUser().getId(),examid);
						}
					
					}
					
				} 
			}
		}
			// 2.查询问题
			String qustrhead = "";
			String qustrbody = "";
			String qustrfoot = "";
			try {
				qustrhead = "<div class='block' style='width:95%;margin-left: 2.5%;'><div class='navbar navbar-inner block-header'><div class='muted pull-left'>试题</div></div><div class='block-content collapse in'><div class='span12'><fieldset>";
				qustrbody = examineeService.getQuestions(exampapersid, examid, examineeid, Integer.parseInt(sort));
				if (TypeChecker.isEmpty(qustrbody)) {
					qustrbody = "<span class='label label-success'><h1>恭喜您，试题已经答完！</h1></span>";
				}
				qustrfoot = "</fieldset></div></div></div>";
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			setAttr("questionstr", qustrhead + qustrbody + qustrfoot);
			// 3.查询答案
			String eqstr = "<div class='block' style='width:95%;margin-left: 2.5%;'><div class='navbar navbar-inner block-header'><div class='muted pull-left'>选题</div></div><div class='block-content collapse in'><div class='span12'>";
			List<ExampapersQuestion> listeq = new ArrayList<ExampapersQuestion>();
			listeq = ExampapersQuestion.dao.find(
					"select * from sys_exampapers_question where exampapers_id = ? order by sort asc ", exampapersid);
			if (listeq.size() > 0) {
				for (int i = 0; i < listeq.size(); i++) {
					List<ExamAnswer> listea = new ArrayList<ExamAnswer>();
					listea = ExamAnswer.dao.find(
							"select * from sys_exam_answer where examinee_id = ? and exam_id = ? and question_id = ? ",
							examineeid, examid, listeq.get(i).getQuestionId());
					if (listea.size() > 0) {
						eqstr = eqstr
								+ "<button type='button' class='btn btn-info' style='margin:3px;' onclick=updateanswer('"
								+ listeq.get(i).getQuestionId() + "','" + listeq.get(i).getSort() + "')>"
								+ listeq.get(i).getSort() + "</button>";
					} else {
						eqstr = eqstr
								+ "<button type='button' class='btn btn-defaul' style='margin:3px;' onclick=updateanswer('"
								+ listeq.get(i).getQuestionId() + "','" + listeq.get(i).getSort() + "')>"
								+ listeq.get(i).getSort() + "</button>";
					}
					if (i > 0 && i % 15 == 0) {
						eqstr = eqstr + "<br />";
					}
				}
			}
			eqstr = eqstr + "</div></div></div>";
			setAttr("eqstr", eqstr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		renderJson();
	}

	/**
	 * @author cg 交卷
	 */
	public void getHandExam() {
		try {
			String examineeid = getPara("examineeid");
			String examid = getPara("examid");
			String exampapersid = getPara("exampapersid");
			Examinee examinee = new Examinee();
			if (!TypeChecker.isEmpty(examineeid)) {
				List<ExamAnswer> answerList = new ArrayList<ExamAnswer>();
				answerList = ExamAnswer.dao.find("select * from sys_exam_answer where examinee_id = ? and exam_id = ? ",
						examineeid, examid);
				Double sumsocres = 0.0;
				if (answerList.size() > 0) {
					for (ExamAnswer examAnswer : answerList) {
						Questions questions = Questions.dao.findById(examAnswer.getQuestionId());
						if (!TypeChecker.isEmpty(questions)) {
							if (examAnswer.getAnswerinfo().equals(questions.getQuestionanswer())){ 
								ExampapersQuestion exampapersQuestion = ExampapersQuestion.dao
										.getExampapersQuestion(examAnswer.getQuestionId(), exampapersid);
								try {
									if (!TypeChecker.isEmpty(exampapersQuestion)) {
										sumsocres = DoubleUtil.add(sumsocres, exampapersQuestion.getScores());
										examAnswer.setAnswerscores(exampapersQuestion.getScores());
										examAnswer.setUpdateBy(getUser().getId());
										examAnswer.setUpdateDate(new Date());
										examAnswer.update();
									}
									System.out.println("自动判卷成功！");
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						}
					}

				}
				examinee = Examinee.dao.findById(examineeid);
				examinee.setStatus(2);
				examinee.setTotalscore(sumsocres);
				examinee.update();
			}
			int flag = examineeService.changeGet(examid);
			if(flag==0){
				int fcg=achievementService.passJudgeList(examid, examineeid, getUser());
				System.out.println(fcg==0?"自动判卷成功！":"判卷失败！");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setAttr("message", "交卷成功！");
		renderJson();
	}

}
