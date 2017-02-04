package com.wide.baseproject.resource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.resource.service.ItemBankService;
import com.wide.baseproject.resource.service.QuestionsService;
import com.wide.baseproject.resource.service.SubjectService;
import com.wide.baseproject.sys.service.DictService;
import com.wide.common.model.Dict;
import com.wide.common.model.Itembank;
import com.wide.common.model.Questionoptions;
import com.wide.common.model.Subject;
import com.wide.common.model.query.QueryItemBank;
import com.wide.common.model.query.QuerySubject;
import com.wide.common.model.simple.ImportDAX;
import com.wide.common.model.simple.ImportDOX;
import com.wide.common.model.simple.ImportPD;
import com.wide.common.model.simple.ImportTK;
import com.wide.common.model.simple.ImportWD;
import com.wide.util.ExcelUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ItemBankController extends BaseController{
	private static final ItemBankService itembankService = Enhancer.enhance(ItemBankService.class);
	private static final SubjectService subjectService = Enhancer.enhance(SubjectService.class);
	private static final QuestionsService questionsService = Enhancer.enhance(QuestionsService.class);
	private static final DictService dictService = Enhancer.enhance(DictService.class);
	/**
	 * @author cg
	 * 进入题库
	 * */
	public void add(){
		QuerySubject querySubject = new QuerySubject();
		List<Subject> subjectlist = subjectService.getSubjecyListAll(querySubject);
		List<Dict> dictlist = Dict.dao.getDictByType("1002");
		setAttr("subjectlist", subjectlist);
		setAttr("dictlist", dictlist);
		render("itembankList.jsp");
	}
	
	/**
	 * @author cg
	 * 查询题库
	 * */
	public void find(){
		QueryItemBank queryitembank = new QueryItemBank();
		queryitembank.setName(getPara("name"));
		queryitembank.setQuestionType(getParaToInt("questiontype"));
		queryitembank.setSubjectid(getPara("subjectid"));
		DataTablesModel itembank = itembankService.getPageItemBank(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryitembank);
		this.renderJson(itembank);
		
	}
	
	/**
	 * @author cg
	 * 题库基本信息
	 * 
	 * */
	public void addinfo(){
		String id = getPara("id");
		Itembank itembank = null;
		QuerySubject querySubject = new QuerySubject();
		List<Subject> subjectlist = subjectService.getSubjecyListAll(querySubject);
		if(id!=null&&!id.equals("")){
			itembank = Itembank.dao.findById(id);
		}else{
			itembank = new Itembank();
		}
		List<Dict> dictlist = Dict.dao.getDictByType("1002");
		setAttr("itembank", itembank);
		setAttr("flagcg", getPara("flagcg"));
		setAttr("subjectlist", subjectlist);
		setAttr("dictlist", dictlist);
		render("itembankInfo.jsp");
	}
	
	/**
	 * @author cg
	 * 提交题库
	 * */
	public void save(){
		int flagcg=0;
		try{
			Itembank itembank = getModel(Itembank.class)==null||getModel(Itembank.class).equals("")?new Itembank():getModel(Itembank.class);
			if(itembank.getId()!=null&&!itembank.getId().equals("")){
				itembank.setUpdateBy(getUser().getId());
				itembank.setUpdateDate(new Date());
				itembank.update();
			}else{
				itembank.setId(createUUid());
				itembank.setCreatorId(getUser().getId());
				itembank.setCreateDate(new Date());
				itembank.setUpdateBy(getUser().getId());
				itembank.setUpdateDate(new Date());
				itembank.setIsdel(0);
				itembank.save();
			}
			flagcg =1;
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		redirect("/item/addinfo?flagcg="+flagcg, true);
		
	}
	
	/**
	 * @author cg
	 * 删除题库
	 * */
	public void del(){
		returninfo = new RenturnInfo();
		String id = getPara("id");
		try{
			if(id!=null&&!id.equals("")){
				Db.update("update sys_itembank set isdel = 1 where id = ? ",id);
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
	/**
	 * @author cg
	 * 导入题库
	 * 
	 * */
	public void importExcel(){
		returninfo = new RenturnInfo();
		List<Subject> subjectlist = new ArrayList<Subject>();
		subjectlist = Subject.dao.getAllSubject();
		String questiontypename= "";
		List<Questionoptions> questionoptionslist  =null;
		Itembank itembank =null;
		
		setAttr("questiontypename",questiontypename);
		setAttr("itembank",itembank);
		setAttr("subjectlist",subjectlist);
		setAttr("numcount",questionoptionslist!=null&&!questionoptionslist.equals("")?questionoptionslist.size():0);
		setAttr("questionoptionslist",questionoptionslist);
		setAttr("flagcg", getPara("flagcg"));

		render("itembankImport.jsp");
		//renderJson();
	}
	
	/**
	 * 下载模板
	 * @param response
	 */
	public void downloadExcel(){
		String questiontypestr = getPara("questiontype");
		int questiontype =!TypeChecker.isEmpty(questiontypestr)?Integer.parseInt(questiontypestr):0;
		String path ="";
			if(questiontype==1){
				path = ItemBankController.class.getResource("").getPath()+"ImportDAXTemplate.xls";
			}else if(questiontype==2){
				path = ItemBankController.class.getResource("").getPath()+"ImportDOXTemplate.xls";
			}else if(questiontype==3){
				path = ItemBankController.class.getResource("").getPath()+"ImportPDTemplate.xls";
			}else if(questiontype==4){
				path = ItemBankController.class.getResource("").getPath()+"ImportTKTemplate.xls";
			}else if(questiontype==5){
				path = ItemBankController.class.getResource("").getPath()+"ImportWDTemplate.xls";
			}
		 File file = new File(path);
		    if (file.isFile()) {
		        renderFile(file);
		        return;
		    }
	    // return;
	    renderNull();
		
	}
	
	/**
	 * 上传Excel
	 * 
	 * */
	 public void uploadExcel() {
		 	int flagcg=0;
	        UploadFile file = this.getFile();
	        File source = file.getFile();
	     // String fileName = file.getFileName();
	        String subjectid = getPara("subjectid");
		 	String itembankId = getPara("itembankId");
		 	String questiontype = getPara("questiontype");
	        int questiontypeint = !TypeChecker.isEmpty(questiontype)?Integer.parseInt(questiontype):0;
	        if(questiontypeint>0){
	        	if(questiontypeint ==1){
	        		List<ImportDAX> listdax=itembankService.checkExcelDax(source,subjectid,itembankId,questiontypeint,getUser());
	        		if(listdax.size()>0){
	        			setAttr("listdax", listdax);
	        		}
	        	}else if(questiontypeint == 2){
	        		List<ImportDOX> listdox=itembankService.checkExcelDox(source,subjectid,itembankId,questiontypeint,getUser());
	        		if(listdox.size()>0){
	        			setAttr("listdox", listdox);
	        		}
	        	}else if(questiontypeint == 3){
	        		List<ImportPD> listpd=itembankService.checkExcelPd(source,subjectid,itembankId,questiontypeint,getUser());
	        		if(listpd.size()>0){
	        			setAttr("listpd", listpd);
	        		}
	        	}else if(questiontypeint == 4){
	        		List<ImportTK> listtk=itembankService.checkExcelTk(source,subjectid,itembankId,questiontypeint,getUser());
	        		if(listtk.size()>0){
	        			setAttr("listtk", listtk);
	        		}
	        	}else if(questiontypeint == 5){
	        		List<ImportWD> listwd=itembankService.checkExcelWd(source,subjectid,itembankId,questiontypeint,getUser());
	        		if(listwd.size()>0){
	        			setAttr("listwd", listwd);
	        		}
	        	}
	        	flagcg =1;
	        }
	        setAttr("flagcg", flagcg);
			render("itembankImport.jsp");
	    }

	
	/**
	 * @author cg
	 * 二级联动查询题库
	 * */
	public void getSelectSubject(){
		String subjectid= getPara("id");
		List<Itembank> list  = new ArrayList<Itembank>();
		list= ItemBankService.getItembankBySubjectId(subjectid);
		renderJson(list);
	}
	/**
	 * @author cg
	 * 根据题库编码查询试题类型
	 * */
	public void getQuestionTypeByItemBankId(){
		String itemBankId= getPara("id");
		Itembank itembank=Itembank.dao.findById(itemBankId);
		Dict questiontype = Dict.dao.getDictObjBykeyType(itembank!=null&&!itembank.equals("")?itembank.getQuestiontype()+"":"", "1002");
		setAttr("questiontype", questiontype);
		setAttr("itembank",itembank);
		renderJson();
	}

}
