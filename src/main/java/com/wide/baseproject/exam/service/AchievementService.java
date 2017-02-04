package com.wide.baseproject.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.ExamAnswer;
import com.wide.common.model.Examinee;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExaminee;
import com.wide.constant.EnumExamType;
import com.wide.constant.EnumExamineeType;
import com.wide.util.DoubleUtil;
import com.wide.viewmodel.DataTablesModel;

public class AchievementService {

	public DataTablesModel getPageExamineeJudge(int pageNum, int pageSize, QueryExaminee queryExaminee) {
		// TODO Auto-generated method stub
		// select = "select
		// t1.id,t.code,t.name,t.starttime,t.endtime,t1.name,t1.totalscore,t1.status
		// ";
		DataTablesModel examineepage = Examinee.dao.pageDataTables(pageNum, pageSize, queryExaminee, 2);
		if (examineepage != null && !examineepage.equals("")) {
			List<List<String>> rows = examineepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = examineepage.getIds().get(i) + "";
					Exam exam = new Exam();
					exam = Exam.dao.findById(row.get(8));
					row.set(2, row.get(2).split("[.]")[0]);
					row.set(3, row.get(3).split("[.]")[0]);
					row.set(6, EnumExamineeType.getFromKey(Integer.parseInt(row.get(6))));
					if (exam.getStatus() == 2) {
						if (Integer.parseInt(row.get(7)) == 0) {
							row.set(7, "<button type='button' class='btn btn-primary' onclick=addJudgeList('" + id
									+ "','"+exam.getId()+"')>人工复评</button>");
						} else {
							row.set(7, "人工复评完成");
						}
					} else {
						row.set(7, "考试未结束不能进行人工复评");
					}
					row.remove(8);
				}
			}
		}
		return examineepage;
	}

	public DataTablesModel getPageJudgeSalf(int pageNum, int pageSize, QueryExaminee queryExaminee) {
		// TODO Auto-generated method stub
		DataTablesModel examineepage = ExamAnswer.dao.pageDataTables(pageNum, pageSize, queryExaminee);
		if (examineepage != null && !examineepage.equals("")) {
			List<List<String>> rows = examineepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = examineepage.getIds().get(i) + "";
					String judgetype = row.get(6);
					if (row.get(2).equals("0") || row.get(2) == "0") {
						row.set(2, "错");
					} else if (row.get(2).equals("1") || row.get(2) == "1") {
						row.set(2, "对");
					}
					if (row.get(4).equals("0") || row.get(4) == "0") {
						row.set(4, "错");
					} else if (row.get(4).equals("1") || row.get(4) == "1") {
						row.set(4, "对");
					}
					List<Questionoptions> list = Questionoptions.dao.findByQuestionId(id);
					String option = "";
					if (list.size() > 0) {
						for (Questionoptions q : list) {
							option = option + "</br>" + q.getCode() + "、" + q.getContant() + "。";
						}
					}
					row.set(1, row.get(1) + option);
					if (Integer.parseInt(judgetype) == 0) {
						row.set(5, "<input class='input-mini' name='score_" + i + "' id='score_" + i
								+ "' type='number' value='" + row.get(5) + "'>");
						row.set(6, "<button type='button' class='btn btn-success' onclick=addpass('" + id + "','score_"
								+ i + "')>人工复评通过</button>");
					} else {
						row.set(6, "已通过人工复评");
					}

				}
			}
		}
		return examineepage;
	}

	public DataTablesModel getPageExamRecord(int pageNum, int pageSize, QueryExaminee queryExaminee) {
		// TODO Auto-generated method stub
		DataTablesModel examineepage = Exam.dao.pageExamRecord(pageNum, pageSize, queryExaminee);
		// t.id,t.code,t.name,t.starttime, t.endtime,t1.examineename ,t1.totalscore ,t1.status,t1.isfinishjudge 
		if (examineepage != null && !examineepage.equals("")) {
			List<List<String>> rows = examineepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = examineepage.getIds().get(i) + "";
					String examineetype = row.get(6);
					int isfinishjudge = Integer.parseInt(row.get(7));
					row.set(2, row.get(2).split("[.]")[0]+" 至 "+row.get(3).split("[.]")[0]);
					row.set(3, EnumExamType.getFromKey(Exam.dao.findById(id).getStatus()));
					row.set(5, isfinishjudge==0?"试卷未判完":row.get(5));
					row.set(6, EnumExamineeType.getFromKey(Integer.parseInt(examineetype)));
					row.set(7,isfinishjudge==0?"试卷未判完":"试卷已判完");
					//row.add(8,"<button type='button' class='btn btn-primary' onclick=checkinfo('"+id+"')>详细</button>");
				}
			}
		}
		return examineepage;
	}

	/**
	 * @author cg
	 * @param examid
	 * @param examineeid
	 * @param User
	 * @return flag 0为保存成功 -1为保存失败
	 * 选择题自动判卷
	 * */
	public int passJudgeList(String examid,String examineeid,User user){
		Double sumscores = 0.0;
		int flag = 0;
		try{
			List<ExamAnswer> ealist = new ArrayList<ExamAnswer>();
			ealist = ExamAnswer.dao.find("select * from sys_exam_answer where exam_id = ? and examinee_id = ? ",examid,examineeid);
			if(ealist.size()>0){
				for(ExamAnswer ea:ealist){
					ea.setJudgetype(1);
					ea.setJudgepeopleid(user.getId());
					ea.setJudgepeoplename(user.getName());
					ea.setJudgetime(new Date());
					ea.setUpdateBy(user.getId());
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
			examinee.setUpdateBy(user.getId());
			examinee.setUpdateDate(new Date());
			examinee.update();
		}catch(Exception ex){
			ex.printStackTrace();
			flag = -1;
		}
		return flag;
	}
}
