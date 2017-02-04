package com.wide.baseproject.exam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jfinal.plugin.activerecord.Db;
import com.wide.common.model.Exam;
import com.wide.common.model.Exampapers;
import com.wide.common.model.ExampapersQtypes;
import com.wide.common.model.ExampapersQuestion;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.query.QueryExampapers;
import com.wide.constant.EnumExamineeType;
import com.wide.util.CGUtil;
import com.wide.util.DoubleUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ExampapersService {

	public DataTablesModel getPageExampapers(int pageNum, int pageSize, QueryExampapers queryExampapers) {
		// TODO Auto-generated method stub
		DataTablesModel invigilatepage = Exampapers.dao.pageDataTables(pageNum, pageSize, queryExampapers);
		if (invigilatepage != null && !invigilatepage.equals("")) {
			List<List<String>> rows = invigilatepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					List<ExampapersQuestion> eqlist = new ArrayList<ExampapersQuestion>();
					String ischouqu ="";
					String id = row.get(0).trim();
					String sumtotal = row.get(5);
					String sumscore = row.get(3);
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, row.get(3)+" 分");
					row.set(3, row.get(4)+" 次");
					row.set(4, row.get(5)+" 题");
					row.set(5, Integer.parseInt((row.get(6) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					eqlist = ExampapersQuestion.dao.find("select * from sys_exampapers_question where exampapers_id = '"+id+"'");
					int endfinish = 0;
					if(!TypeChecker.isEmpty(row.get(7))){
						endfinish =Integer.parseInt(row.get(7));
					}
					List<Exam> examlist = new ArrayList<Exam>();
					examlist= Exam.dao.find("select * from sys_exam where exampapers_id = ? ",id);
					if(examlist.size()>0){
						row.set(6,"<a href='#' onclick=del('"+id+"') >删除</a>");
						
					}else{
						if(eqlist.size()>0&&endfinish==0){
							Double iio=0.0;//分数
							for(ExampapersQuestion e:eqlist){
								iio =DoubleUtil.add(iio, e.getScores());
							}
							if(Double.parseDouble(sumscore)==iio&&Integer.parseInt(sumtotal)==eqlist.size()){
								ischouqu ="<a href ='#' onclick=queren('"+id+"') > 完成抽取</a> | ";
							}else{
								ischouqu ="<a href ='#' onclick=chouqu('"+id+"') > 继续抽取</a> | ";
							}
						}else if(eqlist.size()>0&&endfinish==1){
							ischouqu ="<a href ='#' onclick=chouqu('"+id+"') > 重新抽取</a> | ";
						}else if(eqlist.size()<0){
							ischouqu ="<a href ='#' onclick=chouqu('"+id+"') > 开始抽取</a> | ";
						}
						row.set(6, ischouqu+"<a href ='#' onclick=edit('"+id+"') >修改</a> | <a href='#' onclick=del('"+id+"') >删除</a>");
					}
					
					row.remove(7);
				}
			}
		}
		return invigilatepage;
	}

	public void saveOrUpdateExampapers(Exampapers exampapers, List<ExampapersQtypes> eqlist, int flag) {
		// TODO Auto-generated method stub
		if(flag==0){
			exampapers.update();
		}else{
			exampapers.save();
		}
		saveExampapersTypes(exampapers.getId(),eqlist);
	}

	private void saveExampapersTypes(String id, List<ExampapersQtypes> eqlist) {
		// TODO Auto-generated method stub
		List<ExampapersQtypes> list = new ArrayList<ExampapersQtypes>();
		list=ExampapersQtypes.dao.findByExampapersId(id);
		if(list.size()>0){
			for(ExampapersQtypes eql:list){
				eql.delete();
			}
		}
		if(eqlist.size()>0){
			for(ExampapersQtypes eqn:eqlist){
				eqn.save();
			}
		}
	}

	public int getIsScoreAndIsTotal(String exampapersid, String questiontypeid,Double score) {
		// TODO Auto-generated method stub
		List<ExampapersQtypes> listtype = new ArrayList<ExampapersQtypes>();
		List<ExampapersQuestion> listequestion = new ArrayList<ExampapersQuestion>();
		ExampapersQtypes eqt= new ExampapersQtypes();
		Double scores = 0.0 ;
		if(!TypeChecker.isEmpty(exampapersid)&&!TypeChecker.isEmpty(questiontypeid)){
			listtype = ExampapersQtypes.dao.find("select * from sys_exampapers_qtypes where exampapers_id = ? and type_id =? ",exampapersid,questiontypeid);
			listequestion = ExampapersQuestion.dao.find("select t.* from sys_exampapers_question t,sys_questions t1 where t.exampapers_id = ? and t1.id= t.question_id and t1.questiontype = ? ", exampapersid,questiontypeid);
			if(listtype.size()>0){
				eqt = listtype.get(0);
			}
			if(eqt.getSumtotal()<listequestion.size()+1){
				return 2;
			}
			if(listequestion.size()>0){
				for(ExampapersQuestion eq :listequestion){
					scores = DoubleUtil.add(scores, eq.getScores());
				}
			}
			if(scores+score>eqt.getSumscores()){
				return 1;
			}
		}
		return 0;
	}

	public int goAutochoose(String exampapersid, String subjectid, String questiontypeid) {
		List<Questions> listquestion = new ArrayList<Questions>();
		int shnum = 0;
		//1.根据科目查询题库，根据题库查询所有试题
		listquestion = Questions.dao.find("select * from sys_questions where subject_id = ? and questiontype = ? and isdel = 0 and isenable = 1 ",subjectid,questiontypeid);
		List<ExampapersQuestion> listeq = new ArrayList<ExampapersQuestion>();
		listeq  = ExampapersQuestion.dao.find(" select t.* from sys_exampapers_question t,sys_questions t1 where t.exampapers_id = ? and t.question_id = t1.id and t1.questiontype = ? ",exampapersid,questiontypeid);
		
		//2.根据试卷id和试题类型id到试卷试题类型对照表中查询总题数和总分数
		List<ExampapersQtypes> listeqt = ExampapersQtypes.dao.find("select * from sys_exampapers_qtypes where exampapers_id = ? and type_id =? ",exampapersid,questiontypeid);
		ExampapersQtypes eqt = new ExampapersQtypes();
		if(listeqt.size()>0){
			eqt = listeqt.get(0);
		}
		Double score = 0.0;
		//3.对比题目个数，如果题目数小于总题数，择全部抽取，否则择随机抽取
		if(listeq.size()==eqt.getSumtotal()){
			shnum = 1;
		}else{
			if(eqt.getSumtotal()-listeq.size()>listquestion.size()){
				//4.设置分数：如果总分数整除抽取的总题数。
				score = DoubleUtil.div(eqt.getSumscores(), Double.parseDouble(listquestion.size()+""), 1);
				//5.保存每道题和试卷的对应关系和分数存储到试题试卷对照表中。
				saveExampapersQuestions(listquestion,exampapersid,score);
			}else{
				//4.设置分数：如果总分数整除抽取的总题数。
				List<Questions> listccc= new ArrayList<Questions>();
				for(int i = 0;i<eqt.getSumtotal()-listeq.size();i++){
					Random rand = new Random();
					int icc = rand.nextInt(listquestion.size()); //生成0-100以内的随机数
					listccc.add(listquestion.get(icc));
					listquestion.remove(icc);
				}
				score = DoubleUtil.div(eqt.getSumscores(),Double.parseDouble(eqt.getSumtotal()+""),1);
				//5.保存每道题和试卷的对应关系和分数存储到试题试卷对照表中。
				saveExampapersQuestions(listccc,exampapersid,score);
			}
			//6.抽取完成
		}
		return shnum;
	}
	public void saveExampapersQuestions(List<Questions> list,String exampapersid,Double score){
		if(list.size()>0){
			for(Questions q:list){
				ExampapersQuestion eq = new ExampapersQuestion();
				List<ExampapersQuestion> listeq = new ArrayList<ExampapersQuestion>();
				listeq= ExampapersQuestion.dao.find("select * from sys_exampapers_question where exampapers_id = ? and question_id = ? ",exampapersid,q.getId());
				if(listeq.size()>0){
					continue;
				}else{
					eq.setId(CGUtil.createUUid());
					eq.setQuestionId(q.getId());
					eq.setExampapersId(exampapersid);
					eq.setScores(score);
					eq.save();
				}
			}
		}
	}
/**
 * List<Object[]> list = new ArrayList<Object[]>();
		list = Db.query("select t1.id,t2.questiontype from sys_exampapers_question t1,sys_questions t2 where t1.exampapers_id=? and t2.id = t1.question_id ",exampapersid);
		if(list.size()>0){
			for(Object[] occ:list){
				int oint = 0;
				oint=Integer.parseInt(occ[1]+"");
				switch (oint)
				{
				   case 1://单选题
				    	  分支一;
				    	  break;
				   case 2: //多选题
					      分支二;
					      break;
				   case 3: //判断题
					      分支三;
					      break;
				   case 4: //问答题
					      分支三;
					      break;
				   default ://填空题
					   ;
				}
			}
		}
 * 
 * */
	public void toFinishChoose(String exampapersid) {
		// TODO Auto-generated method stub
		int inum = 0;
		for(int i = 1;i<6;i++){
			List<ExampapersQuestion> list = new ArrayList<ExampapersQuestion>();
			list=ExampapersQuestion.dao.find("select t1.* from sys_exampapers_question t1,sys_questions t2 where t1.exampapers_id = ? and t2.questiontype = ? "
					+ " and t2.id = t1.question_id ",exampapersid,i);
			for(ExampapersQuestion eq:list){
				inum = inum+1;
				eq.setSort(inum);
				eq.update();
			}
		}
		
	}

public int goResetAutochoose(String exampapersid, String subjectid, String questiontypeid) {
	// TODO Auto-generated method stub
	int inum = 0;
	List<ExampapersQuestion> list = new ArrayList<ExampapersQuestion>();
	list=ExampapersQuestion.dao.find("select t1.* from sys_exampapers_question t1,sys_questions t2 where t1.exampapers_id = ? and t2.questiontype = ? "
			+ " and t2.id = t1.question_id ",exampapersid,questiontypeid);
	if(list.size()>0){
		for(ExampapersQuestion eq:list){
			eq.delete();
		}
	}
	inum = 1;
	return inum;
}

}
