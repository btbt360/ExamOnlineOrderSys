package com.wide.baseproject.resource.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.common.model.Dict;
import com.wide.common.model.ExampapersQuestion;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryQuestion;
import com.wide.util.CGUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class QuestionsService {

	public DataTablesModel getPageQuestion(int pageNum, int pageSize, QueryQuestion question,int flag) {
		// TODO Auto-generated method stub
		DataTablesModel questionpage = null;
		if(flag==0){
			questionpage = Questions.dao.pageDataTables(pageNum, pageSize, question);
			if (questionpage != null && !questionpage.equals("")) {
				List<List<String>> rows = questionpage.getRows();
				if (rows.size() > 0) {
					for (int i = 0; i < rows.size(); i++) {
						int num = (pageNum-1)*pageSize+(i+1);
						List<String> row = rows.get(i);
						row.add(6, Integer.parseInt((row.get(5) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
						row.add(7, "<a href ='#' onclick=edit('" + row.get(0).trim()
								+ "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>"  );
						List<Questionoptions> list =Questionoptions.dao.findByQuestionId(row.get(0).trim());
						String option = "";
						if(list.size()>0){
							for(Questionoptions q:list){
								option = option + "</br>"+q.getCode()+"、"+q.getContant()+"。";
							}
						}
						row.set(0, "<input type='checkbox' id='check_"+num+"' value='"+row.get(0)+"' />");
						if(Integer.parseInt(row.get(1))<3){
							row.set(4, row.get(3)+option);
						}else if(Integer.parseInt(row.get(1))==3){
							row.set(5, row.get(4).equals("1")?"对":"错");
						}else{
							row.set(4, row.get(3));
							row.set(5, row.get(4));
						}
						row.set(3, row.get(2));
						row.set(2, Dict.dao.getDictByKeyType(row.get(1),"1002"));
						row.set(1, num+"");
						
						
					}
				}
			}
		}else{
			questionpage = Questions.dao.pageDataTablesChoose(pageNum, pageSize, question);
			if (questionpage != null && !questionpage.equals("")) {
				List<List<String>> rows = questionpage.getRows();
				if (rows.size() > 0) {
					for (int i = 0; i < rows.size(); i++) {
						int num = (pageNum-1)*pageSize+(i+1);
						List<String> row = rows.get(i);
						String id =row.get(0).trim();
						row.set(0,num+"");
						List<Questionoptions> list =Questionoptions.dao.findByQuestionId(id);
						String option = "";
						if(list.size()>0){
							for(Questionoptions q:list){
								option = option + "</br>"+q.getCode()+"、"+q.getContant()+"。";
							}
						}
						row.set(2,row.get(2)+option);
						row.add(3,"<input class='input-mini' name='score_"+num+"' type='number' id='score_"+num+"' value='0'>");
						row.add(4,"");
						if(!TypeChecker.isEmpty(question.getExampapersid())){
							List<ExampapersQuestion> eqlist = new ArrayList<ExampapersQuestion>();
							eqlist = ExampapersQuestion.dao.find("select * from sys_exampapers_question where exampapers_id = ? and question_id = ? ",question.getExampapersid(),id);
							if(eqlist.size()>0){
								row.add(3,"<input class='input-mini' name='score_"+num+"' id='score_"+num+"' type='number' value='"+eqlist.get(0).getScores()+"'>");
								row.set(4, "<button class='btn btn-danger' type='button' onclick=removebut('"+id+"','score_"+num+"')  ><i class='icon-remove icon-white'></i> 移除试题</button>");
							}else{
								row.add(3,"<input class='input-mini' name='score_"+num+"' id='score_"+num+"' type='number' value='0'>");
								row.set(4, "<button class='btn btn-success'type='button' onclick=addbut('"+id+"','score_"+num+"')><i class='icon-plus-sign icon-white'></i> 添加试题</button>");
							}
						}
					}
				}
			}
		}
		return questionpage;
	}

	public List<Questionoptions> getQuestionoptionsByQuestionId(String id) {
		// TODO Auto-generated method stub
		return Questionoptions.dao.findByQuestionId(id);
	}
	/**
	 * 保存questions
	 * */
	public void saveOrUpdateQuestion(Questions questions, List<Questionoptions> questionoptionslist,int flag) {
		// TODO Auto-generated method stub
		if(flag==0){
			questions.update();
		}else{
			questions.save();
		}
		saveQuestionoptions(questions.getId(),questionoptionslist);
		
	}


	/**
	 * 保存选项
	 * */
	private void saveQuestionoptions(String id, List<Questionoptions> questionoptionslist) {
		// TODO Auto-generated method stub
		List<Questionoptions> list = new ArrayList<Questionoptions>();
		list=Questionoptions.dao.findByQuestionId(id);
		if(list.size()>0){
			for(Questionoptions qt:list){
				qt.delete();
			}
		}
		if(questionoptionslist.size()>0){
			for(Questionoptions ql:questionoptionslist){
				ql.save();
			}
		}
	}
	
	

}
