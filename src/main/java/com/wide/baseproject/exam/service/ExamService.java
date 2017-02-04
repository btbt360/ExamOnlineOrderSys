package com.wide.baseproject.exam.service;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.wide.common.model.Dict;
import com.wide.common.model.Exam;
import com.wide.common.model.Examinee;
import com.wide.common.model.Exampapers;
import com.wide.common.model.Subject;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExam;
import com.wide.common.model.query.QuerySubject;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.util.TypeChecker;
import com.wide.viewmodel.DataTablesModel;

public class ExamService {

	public DataTablesModel getPageExam(int pageNum, int pageSize, QueryExam queryExam,int flag) {

		// TODO Auto-generated method stub

		DataTablesModel exampage = Exam.dao.pageDataTables(pageNum, pageSize, queryExam,0);

		if (exampage != null && !exampage.equals("")) {
			List<List<String>> rows = exampage.getRows();
			if (rows.size() > 0) {
			for (int i = 0; i < rows.size(); i++) {
	
				List<String> row = rows.get(i);
				String id = row.get(0).trim();
				row.set(0, row.get(1));
				row.set(1, row.get(2));
				row.set(2, row.get(3));
				row.set(3, row.get(4));
				row.set(4, row.get(5)+" 分钟");
				row.set(5, row.get(6));
				row.set(6, row.get(7)+(row.get(8)!=null&&!row.get(8).equals("")?"、"+row.get(8):""));
				row.set(7, "");
				row.set(8, "");
				if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))>0&&row.get(9).equals("2")){
					row.set(7, "<span class='label'>考试结束</span>");
				}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))<0 && (DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))>=0){
					if(row.get(9).equals("0")){
						row.set(7, "<span class='label label-success'>可以开始考试</span>");
						row.set(8, "");
					}else if(row.get(9).equals("1")){
						row.set(7, "<span class='label label-success'>正在考试</span>");
						row.set(8, "");
					}else if(row.get(9).equals("2")){
						row.set(7, "<span class='label'>考试结束</span>");
						row.set(8, "");
					}
				}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))<0&&row.get(9).equals("0")){
					row.set(7, "<span class='label label-info'>未开始考试</span>");
					row.set(8, "<a href ='#' onclick=edit('" + id
							+ "') >修改</a> | <a href='#' onclick=del('" + id + "') >删除</a>"  );
				}else{
					row.set(7, "<span class='label label-info'>未进行考试</span>");
					row.set(8, "");
				}
				row.remove(9);
			   }
			}
		}
		return exampage;
	}
	/**
	 * 保存考试
	 * 
	 * */
	public void saveExam(Exam exam, String[] str, String[] strName, String starttimestr, String endtimestr, User user) {
		// TODO Auto-generated method stub
		if(!TypeChecker.isEmpty(exam.getId())){
			delExamineeByExamId(exam.getId());
			Exam oldexam = Exam.dao.findById(exam.getId());
			Date starttime = DateUtil.toDateTimeNot(starttimestr);
			Date endtime = DateUtil.toDateTimeNot(endtimestr);
			exam.setStarttime(starttime);
			exam.setEndtime(endtime);
			exam.setNumber(str.length);
			exam.setUpdateBy(user.getId());
			exam.setUpdateDate(new Date());
			exam.update();
			if(!oldexam.getExampapersId().equals(exam.getExampapersId())){
				savePapers(exam.getExampapersId());
			}
		}else{
			String id = CGUtil.createUUid();
			Date starttime = DateUtil.toDateTimeNot(starttimestr);
			Date endtime = DateUtil.toDateTimeNot(endtimestr);
			exam.setId(id);
			exam.setCode("KS-"+DateUtil.toDateStr(new Date()).replace("-","")+"-"+CGUtil.getRandomInt());
			exam.setStarttime(starttime);
			exam.setEndtime(endtime);
			exam.setNumber(str.length);
			exam.setCreatorId(user.getId());
			exam.setCreateDate(new Date());
			exam.setUpdateBy(user.getId());
			exam.setUpdateDate(new Date());
			exam.setIsdel(0);
			exam.save();
			savePapers(exam.getExampapersId());
			
		}
		saveExaminee(str,strName,exam.getId(),exam.getExampapersId(),user.getId());
		
	}
	/**
	 * 更新试卷使用次数
	 * 
	 * */
	private void savePapers(String exampapersId) {
		// TODO Auto-generated method stub
		if(StrKit.notBlank(exampapersId)){
			Exampapers ex = Exampapers.dao.findById(exampapersId);
			ex.setUsecount(ex.getUsecount()+1);
			ex.update();
		}
	}
	/**
	 * 清除考生
	 * 
	 * */
	private void delExamineeByExamId(String examid){
		Db.query("set foreign_key_checks=0;");
		Db.update(" delete from sys_examinee where exam_id = ? ", examid);
		Db.query("set foreign_key_checks=1;");
	}
	/**
	 * 保存考生
	 * 
	 * */
	public void saveExaminee(String[] str,String[] strName, String examid,String exampapersid,String userid){
	//	int[] randomint = CGUtil.getSequence(str.length);
		int[] randomint = CGUtil.getSequence(30);
		for(int i=0;i<str.length;i++){
			Examinee examinee = new Examinee();
			examinee.setId(CGUtil.createUUid());
			examinee.setUserId(str[i]);
			examinee.setExamId(examid);
			examinee.setExampapersId(exampapersid);
			examinee.setExamineename(strName[i]);
			examinee.setCreatorId(userid);
			examinee.setCreateDate(new Date());
			examinee.setUpdateBy(userid);
			examinee.setUpdateDate(new Date());
			examinee.setFingerprint("");
			examinee.setTicketcode("");
			examinee.setSeatno(randomint[i]);
			examinee.setMacaddress(Dict.dao.getDictByKeyType(randomint[i]+"", "1013"));
			examinee.setIpaddress("");
			examinee.setTotalscore(0.0);
			examinee.setScoreslevel(0);
			examinee.setIsdel(0);
			examinee.setIsenable(1); //启用
			examinee.save();
		}
	}
	

}
