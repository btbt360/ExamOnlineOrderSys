package com.wide.baseproject.resource.controller;

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
import com.wide.baseproject.resource.service.CaseService;
import com.wide.baseproject.resource.service.ItemBankService;
import com.wide.baseproject.resource.service.QuestionsService;
import com.wide.baseproject.resource.service.SubjectService;
import com.wide.common.model.Dict;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Questions;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QueryQuestion;
import com.wide.common.model.query.QuerySubject;
import com.wide.util.CGUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewOperation;

public class QuestionsController extends BaseController{
	
	private static final QuestionsService questionsService = Enhancer.enhance(QuestionsService.class);
	private static final ItemBankService itembankService = Enhancer.enhance(ItemBankService.class);

	/**
	 * @author cg
	 * 进入试题管理
	 * */
	public void add(){
		String itembid = getPara("itembid");
		Itembank itembank = null;
		if(!TypeChecker.isEmpty(itembid)){
			itembank = Itembank.dao.findById(itembid);
		}
		List<Dict> dictlist = new ArrayList<Dict>();
		dictlist = Dict.dao.getDictByType("1002");
		List<Subject> subjectlist = new ArrayList<Subject>();
		subjectlist = Subject.dao.getAllSubject();
		setAttr("dictlist", dictlist);
		setAttr("itembank", itembank);
		setAttr("subjectlist",subjectlist);
		render("questionList.jsp");
		
	}
	
	/**
	 * @author cg
	 * 查询试题管理
	 * */
	public void find(){
		QueryQuestion question = new QueryQuestion();
		question.setCode(getPara("code"));
		question.setItembankid(getPara("itembankid"));
		question.setQuestionstype(getPara("questionstype"));
		question.setSubjectid(getPara("subjectid"));
		question.setExampapersid("22");
		DataTablesModel questiontpage = questionsService.getPageQuestion(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), question,0);
		this.renderJson(questiontpage);
		
	}
	
	
	/**
	 * @author cg
	 * 添加试题信息
	 * */
	public void addinfo(){
		List<Subject> subjectlist = new ArrayList<Subject>();
		subjectlist = Subject.dao.getAllSubject();
		String id = getPara("id");
		Questions questions = Questions.dao.findById(id!=null&&!id.equals("")?id:"");
		String questiontypename= "";
		List<Questionoptions> questionoptionslist  =null;
		Itembank itembank =null;
		Subject subject = new Subject();
		String subjectname,questioncode = "";
		if(questions!=null&&!questions.equals("")){
			questiontypename = Dict.dao.getDictByKeyType(questions.getQuestiontype()+"", "1002");
			questionoptionslist= questionsService.getQuestionoptionsByQuestionId(id);
			itembank = Itembank.dao.findById(questions.getItembankId());
			subject = Subject.dao.findById(questions.getSubjectId());
			subjectname = subject.getName();
		}
		setAttr("questiontypename",questiontypename);
		setAttr("questions",questions);
		setAttr("itembank",itembank);
		setAttr("subjectlist",subjectlist);
		setAttr("questioncode",questioncode);
		setAttr("numcount",questionoptionslist!=null&&!questionoptionslist.equals("")?questionoptionslist.size():0);
		setAttr("questionoptionslist",questionoptionslist);
		setAttr("flagcg", getPara("flagcg"));
		render("questionInfo.jsp");
	}
	
	/**
	 * @author cg
	 * 保存试题管理
	 * */
	@Before(Tx.class)
	public void save(){
		int flagcg=0;
		try{
			List<Questionoptions> questionoptionslist = new ArrayList<Questionoptions>();
			Questions questions  = getModel(Questions.class)==null||getModel(Questions.class).equals("")?new Questions():getModel(Questions.class);
			int flag=0;
			if(questions.getId()!=null&&!questions.getId().equals("")){
				questions.setUpdateBy(getUser().getId());
				questions.setUpdateDate(new Date());
			}else{
				questions.setId(createUUid());
				questions.setCode(getCodes(questions.getQuestiontype())+"-"+System.currentTimeMillis()+"-"+CGUtil.getRandomInt());
				questions.getItembankId();
				questions.setCreatorId(getUser().getId());
				questions.setCreateDate(new Date());
				questions.setUpdateBy(getUser().getId());
				questions.setUpdateDate(new Date());
				questions.setName(questions.getCode());
				questions.setIsdel(0);
				flag = 1;
				itembankService.updateItembankCount(questions.getItembankId());
			}
			//保存选项目
			if(questions.getQuestiontype()!=null&&questions.getQuestiontype()<3){
				int numcount = getParaToInt("numcount");
				if(numcount>0){
					for(int i = 0 ;i<numcount+1;i++){
						if(getPara("code"+i)!=null&&!getPara("code"+i).equals("")&&getPara("contant"+i)!=null&&!getPara("contant"+i).equals("")){
							Questionoptions qo = new Questionoptions();
							qo.setId(createUUid());
							qo.setCreatorId(getUser().getId());
							qo.setQuestionsId(questions.getId());
							qo.setCreateDate(new Date());
							qo.setUpdateBy(getUser().getId());
							qo.setUpdateDate(new Date());
							qo.setIsenable(1);
							qo.setIsdel(0);
							qo.setCode(getPara("code"+i));
							qo.setContant(getPara("contant"+i));
							questionoptionslist.add(qo);
						}
					}
				}
			}
			questionsService.saveOrUpdateQuestion(questions,questionoptionslist,flag);
			flagcg =1;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		redirect("/questions/addinfo?flagcg="+flagcg, true);
	}
	
	/**
	 * @author cg
	 * 删除试题管理
	 * */
	public void del(){
		returninfo = new RenturnInfo();
		String id = getPara("id");
		try{
			
			if(id!=null&&!id.equals("")){
				if(id.indexOf("|")!=-1){
					String[] idss = id.split("[|]");
					for(String ss: idss){
						if(StrKit.notBlank(ss)){
							Db.update("update sys_questions set isdel = 1 where id = ? ",ss);
						}
					}
				}else{
					Db.update("update sys_questions set isdel = 1 where id = ? ",id);					
				}
			}
			returninfo.setResult(0);
			returninfo.setResultInfo("删除成功！");
		}catch(Exception ex){
			ex.printStackTrace();
			returninfo.setResult(1);
			returninfo.setResultInfo("删除失败！");
		}
		setAttr("returninfo", returninfo);
		renderJson();
		
		
	}

	private String getCodes(int num){
		String kis = "";
		switch(num){
			case 1:kis = "DAX";
			case 2:kis = "DOX";
			case 3:kis = "PD";
			case 4:kis = "TK";
			case 5:kis = "WD";
		}
		return kis;
	}

}
