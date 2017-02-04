package com.wide.baseproject.resource.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.wide.common.model.Cases;
import com.wide.common.model.Dict;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryCase;
import com.wide.common.model.query.QueryItemBank;
import com.wide.common.model.simple.ImportDAX;
import com.wide.common.model.simple.ImportDOX;
import com.wide.common.model.simple.ImportPD;
import com.wide.common.model.simple.ImportTK;
import com.wide.common.model.simple.ImportWD;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.util.ExcelUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ItemBankService {

	public DataTablesModel getPageItemBank(int pageNum, int pageSize, QueryItemBank queryitembank) {
		// TODO Auto-generated method stub
		DataTablesModel queryitempage = Itembank.dao.pageDataTables(pageNum, pageSize, queryitembank);
		
		if (queryitempage != null && !queryitempage.equals("")) {
			List<List<String>> rows = queryitempage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String str = row.get(5)+"";
					row.set(5, Integer.parseInt((row.get(6) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
					row.set(6, "<a href ='#' onclick=checkinfo('" + row.get(0).trim() + "') >查看试题</a> | <a href ='#' onclick=edit('" + row.get(0).trim() + "') >修改</a> | <a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>"  );
					row.set(0, row.get(1));
					//查询科目名称
					Subject sub=Subject.dao.findById(row.get(2));
					row.set(1, sub!=null&&!sub.equals("")?sub.getName():"");//
					row.set(2, row.get(3)+"题");
					//查询数据字典名称
					row.set(3, Dict.dao.getDictByKeyType(row.get(4),"1002"));
					row.set(4, str+"次");
					
				}
			}
		}
		return queryitempage;
		
	}

	public static List<Itembank> getItembankBySubjectId(String subjectid) {
		// TODO Auto-generated method stub
		return Itembank.dao.find("select * from sys_itembank where isdel = 0 and isenable = 1 and subject_id = ? ",subjectid);
	}
	/**
	 * 更新题库试题总数
	 * */
	public void updateItembankCount(String itembankId) {
		// TODO Auto-generated method stub
		Itembank it= Itembank.dao.findById(itembankId);
		if(it!=null&&!it.equals("")){
			it.setSumtotal(it.getSumtotal()+1);
			it.update();
		}
	}

	/**
	 * @author cg
	 * @param questiontype 
	 * @param itembankId 
	 * @param subjectid 
	 * @param source
	 * 单选导入
	 * @return List<ImportDAX>
	 * */
	public List<ImportDAX> checkExcelDax(File source, String subjectid, String itembankId, int questiontype,User user) {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportDAX.class);
		List<ImportDAX> errorlist = new ArrayList<ImportDAX>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportDAX> list = new ArrayList<ImportDAX>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportDAX dax:list){
					Map<String,String> mapbox = new HashMap<String,String>();
					if(!TypeChecker.isEmpty(dax.getOptionA()))mapbox.put("A", dax.getOptionA());
					if(!TypeChecker.isEmpty(dax.getOptionB()))mapbox.put("B", dax.getOptionB());
					if(!TypeChecker.isEmpty(dax.getOptionC()))mapbox.put("C", dax.getOptionC());
					if(!TypeChecker.isEmpty(dax.getOptionD()))mapbox.put("D", dax.getOptionD());
					if(TypeChecker.isEmpty(dax.getQtitle())||TypeChecker.isEmpty(dax.getAnswer())){
						errorlist.add(dax);
					}else{
						Itembank itembank = Itembank.dao.findById(itembankId);
						itembank.setSumtotal(itembank.getSumtotal()+1);
						itembank.update();
						Questions questions = new Questions();
						questions.setId(CGUtil.createUUid());
						questions.setCode("DAX-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
						questions.setItembankId(itembankId);
						questions.setSubjectId(subjectid);
						questions.setQuestionanswer(dax.getAnswer());
						questions.setQuestionanswerinfo(dax.getAnswerinfo());
						questions.setQuestiontype(questiontype);
						questions.setTitle(dax.getQtitle());
						questions.setInfo(dax.getQinfo());
						questions.setIsdel(0);
						questions.setIsenable(1);
						questions.setUpdateBy(user.getId());
						questions.setUpdateDate(new Date());
						questions.setCreateDate(new Date());
						questions.setCreatorId(user.getId());
						questions.setRemark(dax.getRemark());
						questions.save();
						if(!mapbox.isEmpty()){
							for(Map.Entry<String, String> entry:mapbox.entrySet()){
								Questionoptions qo = new Questionoptions();
								qo.setId(CGUtil.createUUid());
								qo.setQuestionsId(questions.getId());
								qo.setCode(entry.getKey());
								qo.setContant(entry.getValue());
								qo.setUpdateBy(user.getId());
								qo.setUpdateDate(new Date());
								qo.setCreateDate(new Date());
								qo.setCreatorId(user.getId());
								qo.setIsenable(1);
								qo.setIsdel(0);
								qo.save();
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.检查导入文件并将错误数据写入新文件中
		//3.导出新文件
		return errorlist;
	}
	/**
	 * @author cg
	 * @param questiontype 
	 * @param itembankId 
	 * @param subjectid 
	 * @param source
	 * 多选导入
	 * @return List<ImportDOX>
	 * */
	public List<ImportDOX> checkExcelDox(File source, String subjectid, String itembankId, int questiontype,User user) {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportDOX.class);
		List<ImportDOX> errorlist = new ArrayList<ImportDOX>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportDOX> list = new ArrayList<ImportDOX>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportDOX dox:list){
					Map<String,String> mapbox = new HashMap<String,String>();
					if(!TypeChecker.isEmpty(dox.getOptionA()))mapbox.put("A", dox.getOptionA());
					if(!TypeChecker.isEmpty(dox.getOptionB()))mapbox.put("B", dox.getOptionB());
					if(!TypeChecker.isEmpty(dox.getOptionC()))mapbox.put("C", dox.getOptionC());
					if(!TypeChecker.isEmpty(dox.getOptionD()))mapbox.put("D", dox.getOptionD());
					if(!TypeChecker.isEmpty(dox.getOptionE()))mapbox.put("E", dox.getOptionE());
					if(!TypeChecker.isEmpty(dox.getOptionF()))mapbox.put("F", dox.getOptionF());
					if(!TypeChecker.isEmpty(dox.getOptionG()))mapbox.put("G", dox.getOptionG());
					if(TypeChecker.isEmpty(dox.getQtitle())||TypeChecker.isEmpty(dox.getAnswer())){
						errorlist.add(dox);
					}else{
						Itembank itembank = Itembank.dao.findById(itembankId);
						itembank.setSumtotal(itembank.getSumtotal()+1);
						itembank.update();
						Questions questions = new Questions();
						questions.setId(CGUtil.createUUid());
						questions.setCode("DOX-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
						questions.setItembankId(itembankId);
						questions.setSubjectId(subjectid);
						questions.setQuestionanswer(dox.getAnswer());
						questions.setQuestionanswerinfo(dox.getAnswerinfo());
						questions.setQuestiontype(questiontype);
						questions.setTitle(dox.getQtitle());
						questions.setInfo(dox.getQinfo());
						questions.setIsdel(0);
						questions.setIsenable(1);
						questions.setUpdateBy(user.getId());
						questions.setUpdateDate(new Date());
						questions.setCreateDate(new Date());
						questions.setCreatorId(user.getId());
						questions.setRemark(dox.getRemark());
						questions.save();
						if(!mapbox.isEmpty()){
							for(Map.Entry<String, String> entry:mapbox.entrySet()){
								Questionoptions qo = new Questionoptions();
								qo.setId(CGUtil.createUUid());
								qo.setQuestionsId(questions.getId());
								qo.setCode(entry.getKey());
								qo.setContant(entry.getValue());
								qo.setUpdateBy(user.getId());
								qo.setUpdateDate(new Date());
								qo.setCreateDate(new Date());
								qo.setCreatorId(user.getId());
								qo.setIsenable(1);
								qo.setIsdel(0);
								qo.save();
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorlist;
	}
	/**
	 * @author cg
	 * @param questiontype 
	 * @param itembankId 
	 * @param subjectid 
	 * @param source
	 * 判断导入
	 * @return List<ImportPD>
	 * */
	public List<ImportPD> checkExcelPd(File source, String subjectid, String itembankId, int questiontype,User user) {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportPD.class);
		List<ImportPD> errorlist = new ArrayList<ImportPD>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportPD> list = new ArrayList<ImportPD>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportPD pd:list){
					if(TypeChecker.isEmpty(pd.getQtitle())||TypeChecker.isEmpty(pd.getAnswer())){
						errorlist.add(pd);
					}else{
						Itembank itembank = Itembank.dao.findById(itembankId);
						itembank.setSumtotal(itembank.getSumtotal()+1);
						itembank.update();
						Questions questions = new Questions();
						questions.setId(CGUtil.createUUid());
						questions.setCode("PD-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
						questions.setItembankId(itembankId);
						questions.setSubjectId(subjectid);
						if(pd.getAnswer().equals("是")||pd.getAnswer().equals("对")){
							questions.setQuestionanswer("1");
						}
						if(pd.getAnswer().equals("否")||pd.getAnswer().equals("错")){
							questions.setQuestionanswer("0");
						}
						questions.setQuestionanswerinfo(pd.getAnswerinfo());
						questions.setQuestiontype(questiontype);
						questions.setTitle(pd.getQtitle());
						questions.setInfo(pd.getQinfo());
						questions.setIsdel(0);
						questions.setIsenable(1);
						questions.setUpdateBy(user.getId());
						questions.setUpdateDate(new Date());
						questions.setCreateDate(new Date());
						questions.setCreatorId(user.getId());
						questions.setRemark(pd.getRemark());
						questions.save();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorlist;
	}
	/**
	 * @author cg
	 * @param questiontype 
	 * @param itembankId 
	 * @param subjectid 
	 * @param source
	 * 填空导入
	 * @return List<ImportTK>
	 * */
	public List<ImportTK> checkExcelTk(File source, String subjectid, String itembankId, int questiontype,User user) {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportTK.class);
		List<ImportTK> errorlist = new ArrayList<ImportTK>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportTK> list = new ArrayList<ImportTK>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportTK tk:list){
					if(TypeChecker.isEmpty(tk.getQtitle())||TypeChecker.isEmpty(tk.getAnswer())){
						errorlist.add(tk);
					}else{
						Itembank itembank = Itembank.dao.findById(itembankId);
						itembank.setSumtotal(itembank.getSumtotal()+1);
						itembank.update();
						Questions questions = new Questions();
						questions.setId(CGUtil.createUUid());
						questions.setCode("TK-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
						questions.setItembankId(itembankId);
						questions.setSubjectId(subjectid);
						questions.setQuestionanswer(tk.getAnswer());
						questions.setQuestionanswerinfo(tk.getAnswerinfo());
						questions.setQuestiontype(questiontype);
						questions.setTitle(tk.getQtitle());
						questions.setInfo(tk.getQinfo());
						questions.setIsdel(0);
						questions.setIsenable(1);
						questions.setUpdateBy(user.getId());
						questions.setUpdateDate(new Date());
						questions.setCreateDate(new Date());
						questions.setCreatorId(user.getId());
						questions.setRemark(tk.getRemark());
						questions.save();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorlist;
	}
	/**
	 * @author cg
	 * @param questiontype 
	 * @param itembankId 
	 * @param subjectid 
	 * @param source
	 * 问答导入
	 * @return List<ImportTK>
	 * */
	public List<ImportWD> checkExcelWd(File source, String subjectid, String itembankId, int questiontype,User user) {
		// TODO Auto-generated method stub
		ExcelUtil excelUtil = new ExcelUtil(ImportWD.class);
		List<ImportWD> errorlist = new ArrayList<ImportWD>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(source);
			List<ImportWD> list = new ArrayList<ImportWD>();
			list = excelUtil.importExcel("", fis);
			if(list.size()>0){
				for(ImportWD wd:list){
					if(TypeChecker.isEmpty(wd.getQtitle())||TypeChecker.isEmpty(wd.getAnswer())){
						errorlist.add(wd);
					}else{
						Itembank itembank = Itembank.dao.findById(itembankId);
						itembank.setSumtotal(itembank.getSumtotal()+1);
						itembank.update();
						Questions questions = new Questions();
						questions.setId(CGUtil.createUUid());
						questions.setCode("WD-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
						questions.setItembankId(itembankId);
						questions.setSubjectId(subjectid);
						questions.setQuestionanswer(wd.getAnswer());
						questions.setQuestionanswerinfo(wd.getAnswerinfo());
						questions.setQuestiontype(questiontype);
						questions.setTitle(wd.getQtitle());
						questions.setInfo(wd.getQinfo());
						questions.setIsdel(0);
						questions.setIsenable(1);
						questions.setUpdateBy(user.getId());
						questions.setUpdateDate(new Date());
						questions.setCreateDate(new Date());
						questions.setCreatorId(user.getId());
						questions.setRemark(wd.getRemark());
						questions.save();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorlist;
	}


}
