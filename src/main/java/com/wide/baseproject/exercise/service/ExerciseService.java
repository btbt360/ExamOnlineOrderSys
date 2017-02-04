package com.wide.baseproject.exercise.service;

import java.util.ArrayList;
import java.util.List;

import com.wide.common.model.Exercise;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QueryExercise;
import com.wide.common.model.query.QuerySubject;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ExerciseService {

	public DataTablesModel getPageExercise(int pageNum, int pageSize, QueryExercise queryExercise) {
		// TODO Auto-generated method stub
		DataTablesModel exercisepage = Exercise.dao.pageDataTables(pageNum, pageSize, queryExercise);
		
		if (exercisepage != null && !exercisepage.equals("")) {
			List<List<String>> rows = exercisepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					Subject subject = new Subject();
					subject = Subject.dao.getSubjectNameById(row.get(2));
					Itembank itembank = new Itembank();
					itembank = Itembank.dao.getItemBankNameById(row.get(3));
					String info = row.get(5);
					String startstr= "";
					if(Integer.parseInt(row.get(4))>Integer.parseInt(row.get(5))){
						startstr = "| <a href='#' onclick=startExercise('" + row.get(0).trim() + "') >开始练习</a>";
					}
					row.set(5, Integer.parseInt((row.get(6) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					row.set(6, "<a href ='#' onclick=edit('"+row.get(0).trim()+"') >修改</a> | <a href='#' onclick=del('"+row.get(0).trim()+"') >删除</a> "+ startstr);
					row.set(0, row.get(1));
					row.set(1, subject.getName());
					row.set(2, itembank.getName());
					row.set(3, row.get(4));
					row.set(4, info);
				}
			}
		}
		return exercisepage;
		
	}

	public List<Questions> findQuestionsByItSu(Exercise exercise,int sort,int flag) {
		// TODO Auto-generated method stub
		List<Questions> questionslist = new ArrayList<Questions>();
				if(flag == 0){
					sort = sort==0?exercise.getAlreadycount():sort;
				}
				questionslist = Questions.dao.find("select * from sys_questions where isdel = 0 and isenable = 1 and subject_id = ? and itembank_id = ? order by create_date desc limit ?,1"
						,exercise.getSubjectId(),exercise.getItembankId(),sort);
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
