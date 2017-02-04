package com.wide.baseproject.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Enhancer;
import com.jfinal.json.JFinalJson;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.exam.service.BespeakExamService;
import com.wide.common.model.Bespeakexam;
import com.wide.common.model.BespeakexamExaminee;
import com.wide.common.model.Cases;
import com.wide.common.model.Exam;
import com.wide.common.model.query.QueryBespeak;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewCalendar;

/**
 * @author cg
 * 预约考试
 * */
public class BespeakExamContrller extends BaseController {

	private static BespeakExamService bespeakExamService = Enhancer.enhance(BespeakExamService.class);
	
	/**
	 * @author cg
	 * 考试管理--考试预约
	 * 
	 * */
	public void add(){
		
		render("bespeaklist.jsp");
	}
	
	/**
	 * @author cg
	 * 考试管理--考试预约
	 * 
	 * */
	public void getbespeak(){
		QueryBespeak qb = new QueryBespeak();
		qb.setStarttime(getPara("starttimes"));
		qb.setEndtime(getPara("endtimes"));
		qb.setName(getPara("name"));
//		qb.setNum(getParaToInt("num"));
//		qb.setBetype(getParaToInt("betype"));
		DataTablesModel bespeakpage = bespeakExamService.getPageBespeak(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), qb);
		renderJson(bespeakpage);
	}
	
	
	/**
	 * @author cg
	 * 新建预约考试
	 * 
	 * */
	public void addbespeak(){
		String id = getPara("id");
		Bespeakexam bespeakexam = null;
		String starttimestr = "";
		String endtimestr = "";
		if(id!=null&&!id.equals("")){
			bespeakexam = Bespeakexam.dao.findById(id);
			starttimestr = DateUtil.toDateTimeStr(bespeakexam.getStarttime());
			endtimestr = DateUtil.toDateTimeStr(bespeakexam.getEndtime());
		}else{
			bespeakexam = new Bespeakexam();
		}
		
		setAttr("flagcg", getPara("flagcg"));
		setAttr("starttimestr", starttimestr);
		setAttr("endtimestr", endtimestr);
		setAttr("bespeakexam", bespeakexam);
		
		render("bespeakinfo.jsp");
	}
	
	/**
	 * @author cg
	 * 保存预约考试
	 * 
	 * */
	public void savebespeak(){
		String starttimestr = getPara("starttimestr");
		String endtimestr = getPara("endtimestr");
		Date starttime = DateUtil.toDateTimeNot(starttimestr);
		Date endtime = DateUtil.toDateTimeNot(endtimestr);
		int flagcg=0;
		try{
			Bespeakexam baBespeakexam = getModel(Bespeakexam.class)==null||getModel(Bespeakexam.class).equals("")?new Bespeakexam():getModel(Bespeakexam.class);
			if(baBespeakexam.getId()!=null&&!baBespeakexam.getId().equals("")){
				baBespeakexam.setUpdateBy(getUser().getId());
				baBespeakexam.setStarttime(starttime);
				baBespeakexam.setEndtime(endtime);
				baBespeakexam.setUpdateDate(new Date());
				baBespeakexam.update();
			}else{
				baBespeakexam.setId(createUUid());
				baBespeakexam.setStarttime(starttime);
				baBespeakexam.setEndtime(endtime);
				baBespeakexam.setCreatorId(getUser().getId());
				baBespeakexam.setCreateDate(new Date());
				baBespeakexam.setUpdateBy(getUser().getId());
				baBespeakexam.setUpdateDate(new Date());
				baBespeakexam.setIsdel(0);
				baBespeakexam.save();
				
			}
			flagcg = 1;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		redirect("/bespeak/add?flagcg="+flagcg, true);
	}
	
	/**
	 * @author cg
	 * 预约考试发布
	 * 
	 * */
	public void releasebespeak(){
		
		renderJson();
	}
	/**
	 * @author cg
	 * 预约考试删除
	 * 
	 * */
	public void deletebespeak(){
		returninfo = new RenturnInfo();
		String id = getPara("id");
		try{
			if(id!=null&&!id.equals("")){
				Db.update("update sys_bespeakexam set isdel = 1 where id = ? ",id);
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
	 * 预约考试查询
	 * 
	 * */
	public void addquerybespeak(){
		render("querybespeak.jsp");
	}
	/**
	 * @author cg
	 * 预约考试查询
	 * 
	 * */
	public void getQuerybespeak(){
		QueryBespeak qb = new QueryBespeak();
		qb.setStarttime(getPara("starttimes"));
		qb.setStarttime(getPara("endtimes"));
		DataTablesModel bespeakpage = bespeakExamService.getPageBespeakQuery(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), qb);
		renderJson(bespeakpage);
		
	}
	
	  /*********************************************************************************************************/
	 /********************************************考生预约考试功能内容**********************************************/
	/*********************************************************************************************************/
	
	/**
	 * @author cg
	 * 考生考试日程预约
	 * 
	 * */
	public void addusersubscribe(){
		try{
			List<ViewCalendar> vclist = new ArrayList<ViewCalendar>();
			vclist = bespeakExamService.usersubscribe(getUser());
			setAttr("vclist",JsonKit.toJson(vclist));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		render("usersubscribe.jsp");
	}
	/**
	 * @author cg
	 * cx考生考试日程预约
	 * 
	 * */
	public void getUsersubscribe(){
		String id = getPara("id");
		try{
			Bespeakexam be = Bespeakexam.dao.findById(id);
			List<BespeakexamExaminee> listeee = bespeakExamService.getBeSList(id,null);
			List<BespeakexamExaminee> listuser = bespeakExamService.getBeSList(id,getUser().getId());
			setAttr("bespeakexam", be);
			setAttr("alreadynum", listeee.size());
			setAttr("flag", listuser.size()>0?true:false);
			setAttr("flagcg",(DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()),
					DateUtil.toDateTimeStr(be.getStarttime()))>0)?false:true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		renderJson();
	}
	/**
	 * @author cg
	 * 我的预约
	 * 
	 * */
	public void addschedulebespeak(){
		
		render("schedulebespeak.jsp");
	}
	/**
	 * @author cg
	 * 我的预约
	 * 
	 * */
	public void schedulebespeak(){
		QueryBespeak qb = new QueryBespeak();
		qb.setStarttime(getPara("starttimes"));
		qb.setEndtime(getPara("endtimes"));
		qb.setUserid(getUser().getId());
		//预约考试
		DataTablesModel bespeakpage = bespeakExamService.getPageBespeak(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), qb);
		renderJson(bespeakpage);
	}
	/**
	 * @author cg
	 * 考生列表预约考试
	 * 
	 * */
	public void addsubscribelist(){
		
		render("subscribelist.jsp");
	}

	/**
	 * @author cg
	 * 考生添加预约考试
	 * 
	 * */
	public void savebespeakinfo(){
		String flag = getPara("flag");
		String id = getPara("id");
		List<BespeakexamExaminee> listuser = bespeakExamService.getBeSList(id,getUser().getId());
		if(listuser.size()>0){
			System.out.println("已经预定");
		}else{
			BespeakexamExaminee be = new BespeakexamExaminee();
			be.setId(createUUid());
			be.setExamId(id);
			be.setExamineeId(getUser().getId());
			be.save();
		}
		renderJson();
	}
	/**
	 * @author cg
	 * 考生取消预约考试
	 * 
	 * */
	public void cancelbespeak(){
		String flag = getPara("flag");//1为日程展示 2为列表展示
		String id = getPara("id");
		List<BespeakexamExaminee> listuser = bespeakExamService.getBeSList(id,getUser().getId());
		if(listuser.size()>0){
			for(BespeakexamExaminee be:listuser){
				be.delete();
			}
		}else{
			System.out.println("无预定");
		}
		renderJson();
	}

}
