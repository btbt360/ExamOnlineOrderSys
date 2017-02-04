package com.wide.baseproject.statistics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.wide.common.model.Cases;
import com.wide.common.model.Exam;
import com.wide.common.model.ExamAnswer;
import com.wide.common.model.Examinee;
import com.wide.common.model.ExampapersQtypes;
import com.wide.common.model.Office;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.query.QueryStatistics;
import com.wide.util.DateUtil;
import com.wide.util.DoubleUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewChartData;
import com.wide.viewmodel.ViewChartZDate;

public class StatisticsService {

	public List<ViewChartData> examineeChartDatas(String starttimes, String endtimes, String examid) {
		// TODO Auto-generated method stub
		List<ViewChartData> listv = new ArrayList<ViewChartData>();
		String whereStr = whereStr(starttimes,endtimes,examid);
		List<Exam> listexam = Exam.dao.find("select * from sys_exam where 1=1 and isdel = 0 and isenable =1 "+whereStr);
		if(listexam.size()>0){
			for(Exam exam:listexam){
				ViewChartData  vcd= new ViewChartData();
				vcd.setKaoshixStr(exam.getName());
				List<Examinee> listNoqualified = Examinee.dao.getExamineeByExamIdType(exam.getId(),0);
				List<Examinee> listQualified = Examinee.dao.getExamineeByExamIdType(exam.getId(),1);
				List<Examinee> listExcellent = Examinee.dao.getExamineeByExamIdType(exam.getId(),2);
				vcd.setNoqualified(listNoqualified.size());
				vcd.setQualified(listQualified.size());
				vcd.setExcellent(listExcellent.size());
				listv.add(vcd);
			}
		}
		return listv;
	}
	
	
	
	public String whereStr(String starttimes, String endtimes, String examid){
		String whereStr = " ";
		if(!StrKit.isBlank(starttimes)){
			whereStr += " and starttime > '"+starttimes+" 00:00:00'";
		}
		if(!StrKit.isBlank(endtimes)){
			whereStr += " and endtime < '"+endtimes+" 23:59:59'";
		}
		if(!StrKit.isBlank(examid)){
			whereStr += " and id = '"+examid+"'";
		}
		return whereStr;
	}



	public List<ViewChartData> dapartmentChartDatas(String starttimes, String endtimes, String examid) {
		// TODO Auto-generated method stub
		List<ViewChartData> listv = new ArrayList<ViewChartData>();
		String whereStr = "";
		if(!StrKit.isBlank(starttimes)){
			whereStr += " and t4.starttime > '"+starttimes+" 00:00:00'";
		}
		if(!StrKit.isBlank(endtimes)){
			whereStr += " and t4.endtime < '"+endtimes+" 23:59:59'";
		}
		if(!StrKit.isBlank(examid)){
			whereStr += " and t4.id = '"+examid+"'";
		}
		List<Office> listoffice =Office.dao.find("select * from sys_office where type=2 and del_flag = 0 ");
		if(listoffice.size()>0){
			for(Office off:listoffice){
				ViewChartData vcd = new ViewChartData();
				vcd.setKaoshixStr(off.getName());
				List<Examinee> listNoqualified = Examinee.dao.getDapartmentExamineeByType(off.getId(),0,whereStr);
				List<Examinee> listQualified = Examinee.dao.getDapartmentExamineeByType(off.getId(),1,whereStr);
				List<Examinee> listExcellent = Examinee.dao.getDapartmentExamineeByType(off.getId(),2,whereStr);
				vcd.setNoqualified(listNoqualified.size());
				vcd.setQualified(listQualified.size());
				vcd.setExcellent(listExcellent.size());
				listv.add(vcd);
			}
		}
		
		return listv;
	}



	public List<ViewChartData> postChartDatas(String starttimes, String endtimes, String examid) {
		// TODO Auto-generated method stub
				List<ViewChartData> listv = new ArrayList<ViewChartData>();
				String whereStr = "";
				if(!StrKit.isBlank(starttimes)){
					whereStr += " and t3.starttime > '"+starttimes+" 00:00:00'";
				}
				if(!StrKit.isBlank(endtimes)){
					whereStr += " and t3.endtime < '"+endtimes+" 23:59:59'";
				}
				if(!StrKit.isBlank(examid)){
					whereStr += " and t3.id = '"+examid+"'";
				}
				List<Office> listoffice =Office.dao.find("select * from sys_office where type=3 and del_flag = 0 ");
				if(listoffice.size()>0){
					for(Office off:listoffice){
						ViewChartData vcd = new ViewChartData();
						vcd.setKaoshixStr(off.getName());
						List<Examinee> listNoqualified = Examinee.dao.getDapartmentExamineeByType(off.getId(),0,whereStr);
						List<Examinee> listQualified = Examinee.dao.getDapartmentExamineeByType(off.getId(),1,whereStr);
						List<Examinee> listExcellent = Examinee.dao.getDapartmentExamineeByType(off.getId(),2,whereStr);
						vcd.setNoqualified(listNoqualified.size());
						vcd.setQualified(listQualified.size());
						vcd.setExcellent(listExcellent.size());
						listv.add(vcd);
					}
				}
		return listv;
	}



	public DataTablesModel getPageErrorCountfind(int pageNum, int pageSize, QueryStatistics queryStatistics) {
		// TODO Auto-generated method stub
				// tring select = "select t.name,t.code,t2.title,t2.questionanswer,count(*),t2.questionanswerinfo ";
				DataTablesModel errorCountpage = ExamAnswer.dao.getPageErrorCountfind(pageNum, pageSize, queryStatistics);
				if (errorCountpage != null && !errorCountpage.equals("")) {
					List<List<String>> rows = errorCountpage.getRows();
					if (rows.size() > 0) {
						for (int i = 0; i < rows.size(); i++) {
							List<String> row = rows.get(i);
							String id = errorCountpage.getIds().get(i)+"";
							List<Questionoptions> list =Questionoptions.dao.findByQuestionId(id.trim());
							String option = "";
							if(list.size()>0){
								for(Questionoptions q:list){
									option = option + "</br>"+q.getCode()+"、"+q.getContant()+"。";
								}
							}
							row.set(2, row.get(2)+option);
						}
					}
				}
			return errorCountpage;
	}



	public DataTablesModel getPageExamCountDapartmentfind(int pageNum, int pageSize,
			QueryStatistics queryStatistics) {
		// TODO Auto-generated method stub
		String whereStr = "";
		if(!StrKit.isBlank(queryStatistics.getStarttime())){
			whereStr += " and t3.starttime > '"+queryStatistics.getStarttime()+" 00:00:00'";
		}
		if(!StrKit.isBlank(queryStatistics.getEndtime())){
			whereStr += " and t3.endtime < '"+queryStatistics.getEndtime()+" 23:59:59'";
		}
		if(!StrKit.isBlank(queryStatistics.getExamid())){
			whereStr += " and t3.id = '"+queryStatistics.getExamid()+"'";
		}
		//1.查询部门的对象
		DataTablesModel officeCountpage = Office.dao.getPageExamCountDapartmentfind(pageNum, pageSize, queryStatistics);
		if(StrKit.notNull(officeCountpage)){
			List<List<String>> rows = officeCountpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = row.get(0);
					row.set(0, row.get(1));
					List<Exam> listexam = new ArrayList<Exam>();
					List<Examinee> listNoqualified = new ArrayList<Examinee>();
					List<Examinee> listQualified = new ArrayList<Examinee>();
					List<Examinee> listExcellent = new ArrayList<Examinee>();
					if(!StrKit.isBlank(id)){
						//2.查询考试次数
						listexam = Exam.dao.findExamByOfficeId(id,whereStr);
						//3.查询考试不合格人数
						listNoqualified = Examinee.dao.getDapartmentExamineeByType(id,0,whereStr);
						//4.查询考试合格人数
						listQualified = Examinee.dao.getDapartmentExamineeByType(id,1,whereStr);
						//5.查询考试优秀人数
						listExcellent = Examinee.dao.getDapartmentExamineeByType(id,2,whereStr);
					}
					row.set(1, listexam.size()+"");
					row.add(2, listNoqualified.size()+"");
					row.add(3, listQualified.size()+"");
					row.add(4, listExcellent.size()+"");
				}
			}
		}
		return officeCountpage;
	}



	public DataTablesModel getPageExamCountPostfind(int pageNum, int pageSize, QueryStatistics queryStatistics) {
		// TODO Auto-generated method stub
		//1.查询岗位的对象
		String whereStr = "";
		if(!StrKit.isBlank(queryStatistics.getStarttime())){
			whereStr += " and t3.starttime > '"+queryStatistics.getStarttime()+" 00:00:00'";
		}
		if(!StrKit.isBlank(queryStatistics.getEndtime())){
			whereStr += " and t3.endtime < '"+queryStatistics.getEndtime()+" 23:59:59'";
		}
		if(!StrKit.isBlank(queryStatistics.getExamid())){
			whereStr += " and t3.id = '"+queryStatistics.getExamid()+"'";
		}
		//1.查询岗位的对象
		DataTablesModel officeCountpage = Office.dao.getPageExamCountPostfind(pageNum, pageSize, queryStatistics);
		if(StrKit.notNull(officeCountpage)){
			List<List<String>> rows = officeCountpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = row.get(0);
					row.set(0, row.get(1));
					List<Exam> listexam = new ArrayList<Exam>();
					List<Examinee> listNoqualified = new ArrayList<Examinee>();
					List<Examinee> listQualified = new ArrayList<Examinee>();
					List<Examinee> listExcellent = new ArrayList<Examinee>();
					if(!StrKit.isBlank(id)){
						//2.查询考试次数
						listexam = Exam.dao.findExamByOfficeId(id,whereStr);
						//3.查询考试不合格人数
						listNoqualified = Examinee.dao.getDapartmentExamineeByType(id,0,whereStr);
						//4.查询考试合格人数
						listQualified = Examinee.dao.getDapartmentExamineeByType(id,1,whereStr);
						//5.查询考试优秀人数
						listExcellent = Examinee.dao.getDapartmentExamineeByType(id,2,whereStr);
					}
					row.set(1, listexam.size()+"");
					row.add(2, listNoqualified.size()+"");
					row.add(3, listQualified.size()+"");
					row.add(4, listExcellent.size()+"");
				}
			}
		}
		return officeCountpage;
	}



	public DataTablesModel getPageExamCountfind(int pageNum, int pageSize, QueryStatistics queryStatistics) {
		// TODO Auto-generated method stub
		//1.查询考生的对象
		String whereStr = " ";
		if(!StrKit.isBlank(queryStatistics.getStarttime())){
			whereStr += " and t1.starttime > '"+queryStatistics.getStarttime()+" 00:00:00'";
		}
		if(!StrKit.isBlank(queryStatistics.getEndtime())){
			whereStr += " and t1.endtime < '"+queryStatistics.getEndtime()+" 23:59:59'";
		}
		if(!StrKit.isBlank(queryStatistics.getExamid())){
			whereStr += " and t1.id = '"+queryStatistics.getExamid()+"'";
		}
		DataTablesModel examineeCountpage = Examinee.dao.getPageExamCountfind(pageNum, pageSize, queryStatistics);
		if(StrKit.notNull(examineeCountpage)){
			List<List<String>> rows = examineeCountpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = row.get(0);
					row.set(0, row.get(1));
					
					List<Examinee> listexam = new ArrayList<Examinee>();
					List<Examinee> listNoqualified = new ArrayList<Examinee>();
					List<Examinee> listQualified = new ArrayList<Examinee>();
					List<Examinee> listExcellent = new ArrayList<Examinee>();
					if(!StrKit.isBlank(id)){
						//2.查询考试次数
						listexam = Examinee.dao.find("select t.* from sys_examinee t ,sys_exam t1  where t.user_id = ? and t1.isdel = 0 and t1.isenable = 1 and t.exam_id = t1.id "+whereStr,id);
						//3.查询考试不合格次数
						listNoqualified = Examinee.dao.find("select t.* from sys_examinee t ,sys_exam t1 where t.user_id = ? and t.scoreslevel= ? and t1.isdel = 0 and t1.isenable = 1 and t.exam_id = t1.id "+whereStr,id,0);
						//4.查询考试合格次数
						listQualified = Examinee.dao.find("select t.* from sys_examinee t ,sys_exam t1 where t.user_id = ? and t.scoreslevel= ? and t1.isdel = 0 and t1.isenable = 1 and t.exam_id = t1.id "+whereStr,id,1);
						//5.查询考试优秀次数
						listExcellent =  Examinee.dao.find("select t.* from sys_examinee t ,sys_exam t1 where t.user_id = ? and t.scoreslevel= ? and t1.isdel = 0 and t1.isenable = 1 and t.exam_id = t1.id "+whereStr,id,2);
					}
					row.set(1, listexam.size()+"");
					row.add(2, listNoqualified.size()+"");
					row.add(3, listQualified.size()+"");
					row.add(4, listExcellent.size()+"");
				}
			}
		}
		return examineeCountpage;
	}



	public List<ViewChartZDate> queryListChartDatas() {
		// TODO Auto-generated method stub
		String year = DateUtil.toDateStr(new Date()).substring(0, 4);
		List<Integer> months = DateUtil.getsMonthList();
		List<ViewChartZDate> list = new ArrayList<ViewChartZDate>();
		if(months.size()>0){
			for(int i : months){
				String mothe= (i+1)<10?"0"+(i+1):(i+1)+"";
				ViewChartZDate vzd = new ViewChartZDate();
				Date ds = DateUtil.toDate(year+"-"+mothe+"-01");
				Map<String, String> map = DateUtil.getFirstday_Lastday_Month(ds);
				List<Object[]> listexaminee = Db.query("select sum(t.totalscore),count(*) from sys_examinee t,sys_exam t1 "
						+ "where t.isdel = 0 and t.isenable = 1 and t.exam_id = t1.id"
						+ " and t1.isdel = 0 and t1.isenable = 1 and t1.starttime > ? and t1.starttime < ? "
						,map.get("first"),map.get("last"));
				if(listexaminee.size()>0){
					Object[] o = listexaminee.get(0);
					Double dsum = StrKit.notNull(o[0])&&!o[0].equals("")?Double.parseDouble(o[0]+""):0.0;
					Double count = StrKit.notNull(o[1])&&!(o[1]+"").equals("")&&!(o[1]+"").equals("0")?Double.parseDouble(o[1]+""):1.0;
					vzd.setDatetime(year+"-"+(i<10?"0"+i:i+""));
					vzd.setAverage(DoubleUtil.div(dsum, count, 2));
				}
				list.add(vzd);
			}
		}
		return list;
	}



	public List<ViewChartData> queryMyChartDatas(String userid) {
		// TODO Auto-generated method stub
		String year = DateUtil.toDateStr(new Date()).substring(0, 4);
		List<Integer> months = DateUtil.getsMonthList();
		List<ViewChartData> list = new ArrayList<ViewChartData>();
		if(months.size()>0){
			for(int i : months){
				ViewChartData vcd = new ViewChartData();
				String mothe= (i+1)<10?"0"+(i+1):(i+1)+"";
				Date ds = DateUtil.toDate(year+"-"+mothe+"-01");
				Map<String, String> map = DateUtil.getFirstday_Lastday_Month(ds);
				List<Examinee> listNoqualified = new ArrayList<Examinee>();
				List<Examinee> listQualified = new ArrayList<Examinee>();
				List<Examinee> listExcellent = new ArrayList<Examinee>();
				//3.查询考试不合格次数
				listNoqualified = Examinee.dao.find("select t.* from sys_examinee t,sys_exam t1 "
						+ "where t.isdel = 0 and t.isenable = 1 and t.exam_id = t1.id"
						+ " and t1.isdel = 0 and t1.isenable = 1 and t1.starttime > ?"
						+ " and t1.starttime < ? and t.scoreslevel= ? and t.user_id = ? "
						,map.get("first"),map.get("last"),0,userid);
				//4.查询考试合格次数
				listQualified = Examinee.dao.find("select t.* from sys_examinee t,sys_exam t1 "
						+ "where t.isdel = 0 and t.isenable = 1 and t.exam_id = t1.id"
						+ " and t1.isdel = 0 and t1.isenable = 1 and t1.starttime > ?"
						+ " and t1.starttime < ? and t.scoreslevel= ? and t.user_id = ? "
						,map.get("first"),map.get("last"),1,userid);
				//5.查询考试优秀次数
				listExcellent =  Examinee.dao.find("select t.* from sys_examinee t,sys_exam t1 "
						+ "where t.isdel = 0 and t.isenable = 1 and t.exam_id = t1.id"
						+ " and t1.isdel = 0 and t1.isenable = 1 and t1.starttime > ?"
						+ " and t1.starttime < ? and t.scoreslevel= ? and t.user_id = ? "
						,map.get("first"),map.get("last"),2,userid);
				vcd.setKaoshixStr(year+"-"+(i<10?"0"+i:i+""));
				vcd.setNoqualified(listNoqualified.size());
				vcd.setExcellent(listExcellent.size());
				vcd.setQualified(listQualified.size());
				list.add(vcd);
			}
		}
		return list;
	}

}
