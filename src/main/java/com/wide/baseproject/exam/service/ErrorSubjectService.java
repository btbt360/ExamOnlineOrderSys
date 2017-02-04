package com.wide.baseproject.exam.service;

import java.util.ArrayList;
import java.util.List;

import com.wide.common.model.Cases;
import com.wide.common.model.query.QueryError;
import com.wide.viewmodel.DataTablesModel;
import com.wide.common.model.Error;
import com.wide.common.model.Exercise;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
public class ErrorSubjectService {

	public DataTablesModel getPageError(int pageNum, int pageSize, QueryError qe) {
		// TODO Auto-generated method stub
		DataTablesModel errorpage = Error.dao.pageDataTables(pageNum, pageSize, qe);
		if (errorpage != null && !errorpage.equals("")) {
			List<List<String>> rows = errorpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = errorpage.getIds().get(i) + "";
					List<Questionoptions> list = Questionoptions.dao.findByQuestionId(row.get(5));
					String option = "";
					if (list.size() > 0) {
						for (Questionoptions q : list) {
							option = option + "</br>" + q.getCode() + "、" + q.getContant() + "。";
						}
					}
					row.set(0,row.get(0)+option);
					row.set(1, Itembank.dao.findById(row.get(1)).getName());
					row.set(5,"<a href='#' onclick=removenerror('"+id+"') >移除</a>");
				}
			}
		}
		return errorpage;
	}

	public List<Questions> findQuestionsByItSu(String userid,int sort, int flag) {
		// TODO Auto-generated method stub
		List<Questions> questionslist = new ArrayList<Questions>();
		questionslist = Questions.dao.find("select t.* from sys_questions t,sys_error t1 where t.isdel = 0 and t.isenable = 1 and t.id = t1.question_id"
				+ " and t1.user_id = ? order by create_date desc limit ?,1"
				,userid,sort);
		if(questionslist.size()>0){
			for(Questions que:questionslist){
				if(que.getQuestiontype()==1){
					List<Questionoptions> list = Questionoptions.dao.findByQuestionId(que.getId());
					String option = "";
					if (list.size() > 0) {
						for (int i=0;list.size()>i;i++) {
							option = option + "</br>"+" <input type='radio' id='options_"+i+"' name='options' value='"+ list.get(i).getCode() + "' onclick=ischeck('"+ list.get(i).getCode() + "') > "+ list.get(i).getCode() + "、" + list.get(i).getContant() + "。";
						}
					}
					que.setTitle(que.getTitle()+option);
				}else if(que.getQuestiontype()==2){
					List<Questionoptions> list = Questionoptions.dao.findByQuestionId(que.getId());
					String option = "";
					if (list.size() > 0) {
						for (int i=0;list.size()>i;i++) {
							option = option + "</br>"+" <input type='checkbox' id='options_"+i+"' name='options_"+i+"' value='"+ list.get(i).getCode() + "' onclick=ischeck('"+ list.get(i).getCode() + "') > "+ list.get(i).getCode() + "、" + list.get(i).getContant() + "。";
						}
					}
					que.setTitle(que.getTitle()+option);
				}else if(que.getQuestiontype()==3){
					String option =  "</br> <input type='radio' id='options_0' name='options' value='0' onclick=ischeck(0) > 错</br><input type='radio' id='options_1' name='options' value='1' onclick=ischeck(1) > 对";
					que.setTitle(que.getTitle()+option);
				}else{
					String option =  "</br> <textarea id='answertext' name='answertext' rows='5' style='width: 50%;' placeholder='请输入答案!' onblur=isanswer()></textarea></div></div>";
					que.setTitle(que.getTitle()+option);
				}
			}
		}
		return questionslist;
	}

}
