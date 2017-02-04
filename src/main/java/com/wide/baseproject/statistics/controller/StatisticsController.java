package com.wide.baseproject.statistics.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.exam.service.ExamService;
import com.wide.baseproject.statistics.service.StatisticsService;
import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.ExamAnswer;
import com.wide.common.model.Examinee;
import com.wide.common.model.Exampapers;
import com.wide.common.model.ExampapersQuestion;
import com.wide.common.model.Office;
import com.wide.common.model.Questions;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryCase;
import com.wide.common.model.query.QueryExam;
import com.wide.common.model.query.QueryStatistics;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewChartData;

public class StatisticsController extends BaseController{
	private static final ExamService examService = Enhancer.enhance(ExamService.class);
	private static final StatisticsService statisticsService = Enhancer.enhance(StatisticsService.class);

	
	/**
	 * @author lubin
	 *  考生成绩统计
	 * 
	 * */
	public void examineeCount(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		setAttr("examlist", examlist);
		render("examineeCount.jsp");
	}
	/**
	 * @author cg
	 * 考生成绩统计图表数据
	 * */
	public void examineeChartDatas(){
		String starttimes = getPara("starttimes");
		String endtimes = getPara("endtimes");
		String examid = getPara("examid");
		List<ViewChartData> list = new ArrayList<ViewChartData>();
		list = statisticsService.examineeChartDatas(starttimes,endtimes,examid);
		renderJson(list);
	}

	/**
	 * @author lubin
	 *  考试按部门统计
	 * 
	 * */
	public void dapartmentCount(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		setAttr("examlist", examlist);
		render("dapartmentCount.jsp");
	}
	
	/**
	 * @author cg
	 * 考试按部门图表数据
	 * */
	public void dapartmentChartDatas(){
		String starttimes = getPara("starttimes");
		String endtimes = getPara("endtimes");
		String examid = getPara("examid");
		List<ViewChartData> list = new ArrayList<ViewChartData>();
		list = statisticsService.dapartmentChartDatas(starttimes,endtimes,examid);
		renderJson(list);
	}
	
	/**
	 * @author lubin
	 *  考试按岗位统计
	 * 
	 * */
	public void postCount(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		setAttr("examlist", examlist);
		render("postCount.jsp");
	}
	
	/**
	 * @author cg
	 * 考试按岗位图表数据
	 * */
	public void postChartDatas(){
		String starttimes = getPara("starttimes");
		String endtimes = getPara("endtimes");
		String examid = getPara("examid");
		List<ViewChartData> list = new ArrayList<ViewChartData>();
		list = statisticsService.postChartDatas(starttimes,endtimes,examid);
		renderJson(list);
	}
	
	/**
	 * @author lubin
	 *  按错误率统计
	 * 
	 * */
	public void errorrateCount(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		setAttr("examlist", examlist);
		render("errorrateCount.jsp");
	}
	
	public void errorrateCountfind(){
		QueryStatistics queryStatistics = new QueryStatistics();
		String examid = getPara("examid");
		queryStatistics.setExamid(examid);
		DataTablesModel casepage = statisticsService.getPageErrorCountfind(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryStatistics);
		this.renderJson(casepage);
	}
	


	
	/**
	 * @author lubin
	 *  部门考试统计
	 * 
	 * */
	public void examCountDapartment(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		List<Dict> dictlit = Dict.dao.getDictByType("1015");
		setAttr("examlist", examlist);
		setAttr("dictlit", dictlit);
		render("examCountDapartment.jsp");
	}
	/**
	 * @author lubin
	 *  部门考试统计
	 * 
	 * */
	public void examCountDapartmentfind(){
		QueryStatistics queryStatistics = new QueryStatistics();
		queryStatistics.setExamid(getPara("examid"));
		queryStatistics.setStarttime(getPara("starttime"));
		queryStatistics.setEndtime(getPara("endtime"));
		DataTablesModel casepage = statisticsService.getPageExamCountDapartmentfind(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryStatistics);
		this.renderJson(casepage);
	}
	
	/**
	 * @author lubin
	 *  岗位考试统计
	 * 
	 * */
	public void examCountPost(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		List<Dict> dictlit = Dict.dao.getDictByType("1015");
		setAttr("examlist", examlist);
		setAttr("dictlit", dictlit);
		render("examCountPost.jsp");
	}
	/**
	 * @author lubin
	 *  岗位考试统计
	 * 
	 * */
	public void examCountPostfind(){
		QueryStatistics queryStatistics = new QueryStatistics();
		queryStatistics.setExamid(getPara("examid"));
		queryStatistics.setStarttime(getPara("starttime"));
		queryStatistics.setEndtime(getPara("endtime"));
		DataTablesModel casepage = statisticsService.getPageExamCountPostfind(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryStatistics);
		this.renderJson(casepage);
	}
	/**
	 * @author lubin
	 *  人员考试统计
	 * 
	 * */
	public void examCount(){
		List<Exam> examlist =  Exam.dao.find("select * from sys_exam ");
		List<Dict> dictlit = Dict.dao.getDictByType("1015");
		setAttr("examlist", examlist);
		setAttr("dictlit", dictlit);
		render("examCount.jsp");
	}
	/**
	 * @author lubin
	 *  岗位考试统计
	 * 
	 * */
	public void examCountfind(){
		QueryStatistics queryStatistics = new QueryStatistics();
		queryStatistics.setExamid(getPara("examid"));
		queryStatistics.setStarttime(getPara("starttime"));
		queryStatistics.setEndtime(getPara("endtime"));
		DataTablesModel casepage = statisticsService.getPageExamCountfind(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryStatistics);
		this.renderJson(casepage);
	}	
}
