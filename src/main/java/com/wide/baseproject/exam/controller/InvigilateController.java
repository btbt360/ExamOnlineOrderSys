package com.wide.baseproject.exam.controller;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wide.base.BaseController;
import com.wide.baseproject.exam.service.AchievementService;
import com.wide.baseproject.exam.service.ExamineeService;
import com.wide.baseproject.exam.service.InvigilateService;
import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.Examinee;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExam;
import com.wide.common.model.query.QueryExaminee;
import com.wide.config.UserToken;
import com.wide.util.CGUtil;
import com.wide.viewmodel.DataTablesModel;

public class InvigilateController  extends BaseController {

	private static final InvigilateService invigilateService = Enhancer.enhance(InvigilateService.class);
	private static final ExamineeService examineeService = Enhancer.enhance(ExamineeService.class);
	private static final AchievementService achievementService = Enhancer.enhance(AchievementService.class);
	
	/**
	 * @author cg
	 * 监考页面
	 * */
	public void add(){
		
		setAttr("username", getUser().getName());
		render("InvigilateList.jsp");
	}
	
	/**
	 * @author cg
	 * 监考页面
	 * */
	public void addmonitoringonline(){
		String examId=getPara("id");
		Exam ex = new Exam();
		ex = Exam.dao.findById(examId);
		List<Examinee> elist = new ArrayList<Examinee>();
		elist=Examinee.dao.find("select * from sys_examinee where isdel = 0 and isenable = 1 and exam_id = '"+examId+"'");
		setAttr("elist",elist);
		setAttr("examId",examId);
		setAttr("exam", ex);
		render("monitoringonline.jsp");
	}
	
	/**
	 * @author cg
	 * 监考图表
	 * */
	public void getTable(){
		QueryExam queryExam = new QueryExam();
		queryExam.setName(getPara("name"));
		queryExam.setStarttimes(getPara("starttimes"));
		queryExam.setEndtimes(getPara("endtimes"));
		DataTablesModel invigilatepage = invigilateService.getPageInvigilate(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryExam,1);
		this.renderJson(invigilatepage);
		
	}
	
	/**
	 * @author cg
	 * 考生查询
	 * 
	 * */
	public void getEETable(){
		QueryExaminee queryExaminee = new QueryExaminee();
		queryExaminee.setName(getPara("name"));
		queryExaminee.setExamineeId(getPara("examineeId"));
		queryExaminee.setExamId(getPara("examId"));
		DataTablesModel invigilatepage = invigilateService.getPageExaminee(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryExaminee);
		this.renderJson(invigilatepage);
	}
	
	/**
	 * @author cg
	 * 开始考试
	 * */
	public void gettoStart(){
		String id = getPara("id");
		if(id!=null&&!id.equals("")){
			Db.update("update sys_examinee set status = 1 where exam_id = '"+id+"'");
		}
		Exam exam = Exam.dao.findById(id);
		exam.setEnddistancetime(Integer.parseInt((exam.getDuration()*60+"").split("[.]")[0]));
		exam.setStatus(1);
		exam.update();
		MyThread myThread = new MyThread(exam.getId(),getUser());
		myThread.start();
		setAttr("message", exam.getDuration());
		renderJson();
	}
	
	/**
	 * @author cg
	 * 结束考试
	 * */
	@Before(Tx.class)
	public void gettofinish(){
		String id = getPara("id");
		try{
			invigilateService.gettofinish(id);
			int flag = examineeService.changeGet(id);
			List<Examinee> examineelist = new ArrayList<Examinee>();
			examineelist = Examinee.dao.find("select * from sys_examinee where exam_id = ? ",id);
			if(examineelist.size()>0){
				for(Examinee e:examineelist){
					if(flag==0){
						int fcg=achievementService.passJudgeList(id, e.getId(), getUser());
						System.out.println(fcg==0?"自动判卷成功！":"判卷失败！");
					}
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		setAttr("message", "考试结束");
		renderJson();
	}
	
	/**
	 * @author cg
	 * 考生下机
	 * */
	public void getgoDown(){
		String id = getPara("id");
		if(id!=null&&!id.equals("")){
			Db.update("update sys_examinee set status = 4 where id = '"+id+"'");
		}
		setAttr("message", "该考生已经下机");
		renderJson();
	}
	
	/**
	 * @author cg
	 * 考生指纹录入
	 * */
	public void getfingerprint(){
		String id = getPara("id");
		String fingerpath =getPara("fingerpath");
		int p = 0;
		if(id!=null&&!id.equals("")){
			p=invigilateService.getfingerprint(id,fingerpath);
		}
		if(p==0){
			setAttr("message", "该考生指纹未在系统内录入或录入错误");
		}else{
			setAttr("message", "该考生指纹录入正确");
		}
		
		renderJson();
	}
	
	/**
	 * @author cg
	 * 考生头像上传
	 * */
	public void getSculpturepath(){
		String id = getPara("id");
		String sculpturepath =getPara("sculpturepath");
		if(id!=null&&!id.equals("")){
			Db.update("update sys_examinee set sculpturepath ='"+sculpturepath+"' where id ='"+id+"'");
		}
		setAttr("message", "该考生头像上传成功！");
		renderJson();
	}
	
	/**
	 * @author cg
	 * 考试剩余时间更新
	 * */
	public void getRemainingTime(){
		String id = getPara("id");
		int enddistancetime = getParaToInt("enddistancetime");
		if(id!=null&&!id.equals("")){
			Db.update("update sys_exam set enddistancetime ="+enddistancetime+" where id ='"+id+"'");
		}
		setAttr("message", "更新成功！");
		renderJson();
	}
	
	/**
	 * @author cg
	 * 考生缺考
	 * */
	public void gettoAbsent(){
		String id = getPara("id");
		if(id!=null&&!id.equals("")){
			Db.update("update sys_examinee set status = 3 where id = '"+id+"'");
		}
		setAttr("message", "该考生已经视为缺考");
		renderJson();
	}	
	
	/**
	 * @author cg
	 * 查询考生列表
	 * */
	public void getExamineeList(){
		String name=getPara("name");
		String examId=getPara("examId");
		List<Examinee> elist = new ArrayList<Examinee>();
		elist=Examinee.dao.find("select * from sys_examinee where name like %'"+name+"'% and exam_id ='"+examId+"'");
		renderJson(elist);
	}	

	/**
	 * @author cg
	 * 查询考生资格
	 * */
	public void getExaminee(){
		String examId=getPara("id");
		User user =getUser();
		List<Examinee> elist = new ArrayList<Examinee>();
		String macstr ="";
		String ipaddress = getIpAddr(this.getRequest());
		List<Dict> dictlist = new ArrayList<Dict>();
		if(StrKit.notBlank(ipaddress)){
			dictlist = Dict.dao.find("select * from sys_dict where description = ? and type = ? ",ipaddress,"1013");
			if(dictlist.size()>0){
				macstr = dictlist.get(0).getDictvalue();
			}
		}
		//判断mac地址
		Examinee ee = new Examinee();
		Exam em = new Exam();
		em = Exam.dao.findById(examId);
		int flag = 0;
		elist=Examinee.dao.find("select * from sys_examinee where user_id = '"+user.getId()+"' and exam_id ='"+examId+"'");
		if(elist.size()>0){
			ee = elist.get(0);
			flag = ee.getStatus();
		}
		if(em.getStatus()<1){
			flag = 5;
		}
		if(!ee.getMacaddress().equals(macstr)&&StrKit.notBlank(ee.getMacaddress())&&StrKit.notBlank(macstr)){
			flag = 6;
		}
		flag =1;
		setAttr("flag", flag);
		renderJson();
	}
	  //获取ip地址  
    public String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
}

class MyThread extends Thread {
	private static final ExamineeService examineeService = Enhancer.enhance(ExamineeService.class);
	private static final AchievementService achievementService = Enhancer.enhance(AchievementService.class);
	public String examid;
	public User user;
	public MyThread(String examid,User user){
		this.examid = examid;
		this.user = user;
	}
	private void passExam(String examid){
		try{
			int flag = examineeService.changeGet(examid);
			List<Examinee> examineelist = new ArrayList<Examinee>();
			examineelist = Examinee.dao.find("select * from sys_examinee where exam_id = ? ",examid);
			if(examineelist.size()>0){
				for(Examinee e:examineelist){
					if(flag==0){
						int fcg=achievementService.passJudgeList(examid, e.getId(), user);
						System.out.println(fcg==0?"自动判卷成功！":"判卷失败！");
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
    @Override
    public void run() {
    	try{
    		while (true) {
            	System.out.println("---开始考试线程---");
            	//1.开始考试时触发此类
            	Exam exam = Exam.dao.findById(examid);
            	int jsnum  = 0;
            	if(StrKit.notNull(exam)&&exam.getStatus()!=2){
            		jsnum = Integer.parseInt((exam.getDuration()*60+"").replace(".0", ""));
            	}else{
            		this.passExam(examid);
            		System.out.println("---考试结束---");
            		return;
            	}
            	if(jsnum>0){
            		int ksi=0;
            		for(int i= jsnum;i>-1;i--){
                    	this.sleep(1000);
                    	exam.setEnddistancetime(i);
                    	exam.update();
                    	ksi = i;
            		}
            		if(ksi == 0 ){
            			exam.setStatus(2);
            			exam.setEnddistancetime(0);
            			exam.update();
            			this.passExam(examid);
            			System.out.println("---考试结束---");
            			return;
            		}
            	}else{
            		this.passExam(examid);
            		System.out.println("---考试结束---");
            		return;
            	}
            	//2.设置timer开始计时
            	//3.timer来源于查询考试表里的剩余时间
            	//4.每一秒更新一次timer，更新一次剩余时间，直至剩余时间为0。
                if (this.isInterrupted()) {
                    System.out.println("---考试线程停止---");
                    return;
                }
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        
    }
  
}
