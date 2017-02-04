package com.wide.baseproject.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.wide.common.model.Exam;
import com.wide.common.model.Examinee;
import com.wide.common.model.Questions;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryExam;
import com.wide.common.model.query.QueryExaminee;
import com.wide.common.model.Error;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;

public class InvigilateService {

	public DataTablesModel getPageInvigilate(int pageNum, int pageSize, QueryExam queryExam, int flag) {
		// TODO Auto-generated method stub
		DataTablesModel invigilatepage = Exam.dao.pageDataTables(pageNum, pageSize, queryExam, 1);
		if (invigilatepage != null && !invigilatepage.equals("")) {
			List<List<String>> rows = invigilatepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = row.get(0).trim();
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, row.get(3));
					row.set(3, row.get(4));
					row.set(4, row.get(5) + " 分钟");
					row.set(5, row.get(6));
					row.set(6, row.get(7) + (row.get(8) != null && !row.get(8).equals("") ? "、" + row.get(8) : ""));
					row.set(7, "");
					row.set(8, "");
					if ((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3))) > 0
							&& row.get(9).equals("2")) {
						row.set(7, "<span class='label'>考试结束</span>");
						row.set(8, "");
					} else if ((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3))) < 0
							&& (DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2))) >= 0) {
						if (row.get(9).equals("0")) {
							row.set(7, "<span class='label label-success'>可以开始考试</span>");
							if (flag == 0) {
								row.set(8, "<a href ='#' onclick=startExam('" + id + "') >开始考试</a>");
							} else {
								row.set(8, "<a href ='#' onclick=startInvigilate('" + id + "') >开始监考</a>");
							}
						} else if (row.get(9).equals("1")) {
							row.set(7, "<span class='label label-success'>正在考试</span>");
							if (flag == 0) {
								row.set(8, "<a href ='#' onclick=startExam('" + id + "') >开始考试</a>");
							} else {
								row.set(8, "<a href ='#' onclick=startInvigilate('" + id + "') >开始监考</a>");
							}
						} else if (row.get(9).equals("2")) {
							row.set(7, "<span class='label'>考试结束</span>");
							row.set(8, "");
						}
					} else if ((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2))) < 0
							&& row.get(9).equals("0")) {
						row.set(7, "<span class='label label-info'>未开始考试</span>");
						row.set(8, "");
					} else {
						row.set(7, "<span class='label label-info'>未进行考试</span>");
						row.set(8, "");
					}
					row.remove(9);
				}
			}
		}
		return invigilatepage;
	}

	public DataTablesModel getPageExaminee(int pageNum, int pageSize, QueryExaminee queryExaminee) {
		// TODO Auto-generated method stub
		DataTablesModel examineepage = Examinee.dao.pageDataTables(pageNum, pageSize, queryExaminee, 1);
		if (examineepage != null && !examineepage.equals("")) {
			List<List<String>> rows = examineepage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String path = "xFilePath_" + i;
					String id = examineepage.getIds().get(i) + "";
					String type = row.get(5);
					Examinee ee = Examinee.dao.findById(id);
					User user = User.dao.findById(ee.getUserId());
					row.set(3,
							row.get(3) != null && !row.get(3).equals("") ? "指纹匹配成功"
									: "<input type='button' class='btn btn-primary' value='请匹配指纹' onclick=submitVerify('"
											+ user.getFingerprintone() + "','" + id + "') />");
					row.set(4, row.get(4) != null && !row.get(4).equals("")
							? "<img src='" + row.get(4) + "' class='img-rounded' style ='width:120px;height:120px;' />"
							: "<input id='" + path
									+ "' type='text' style='display: table-cell;vertical-align: middle;margin-top:3%;' />&nbsp;&nbsp;<input type='button' class='btn btn-primary' value='选择附件' onclick=BrowseServer('"
									+ path + "','" + id + "') />");
					if (Integer.parseInt(type) < 2 && Integer.parseInt(type) > 0) {
						//row.set(5, "<a href ='#' onclick=goDown('" + id + "') >下机</a> | <a href ='#' onclick=toAbsent('"+ id + "') >缺考</a>");
						row.set(5, "<a href ='#' onclick=toAbsent('"+ id + "') >缺考</a>");
					} else {
						row.set(5, "");
					}
				}
			}
		}
		return examineepage;
	}

	public void gettofinish(String id) {
		// TODO Auto-generated method stub
		List<Examinee> elist = new ArrayList<Examinee>();
		elist = Examinee.dao
				.find("select * from sys_examinee where isdel = 0 and isenable = 1 and exam_id ='" + id + "'");
		if (id != null && !id.equals("")) {
			/**
			 * if(elist.size()>0){ for(Examinee ee:elist){
			 * if(ee.getFingerprint()!=null&&!ee.getFingerprint().equals("")){
			 * Db.update("update sys_examinee set status = 3 where id ='"
			 * +ee.getId()+"'"); } } }
			 **/
			Db.update("update sys_examinee set status = 2 where exam_id = '" + id + "' and status not in (3,4)");
			Exam ex = new Exam();
			ex = Exam.dao.findById(id);
			ex.setStatus(2);
			ex.setEnddistancetime(0);
			ex.update();
		}
	}

	public int getfingerprint(String id, String fingerpath) {
		// TODO Auto-generated method stu
		int p = 0;
		Examinee ee = Examinee.dao.findById(id);
		User user = User.dao.findById(ee != null && !ee.equals("") ? ee.getUserId() : "");
		Db.update("update sys_examinee set fingerprint ='" + fingerpath + "' where id = '" + id + "'");
		return p;
	}

	public void saveError(String questionid, String answer, String userid,String examid) {
		// TODO Auto-generated method stub
		Questions questions = Questions.dao.findById(questionid);
		if (StrKit.notNull(questions)) {
			if (questions.getQuestionanswer() != answer) {
				List<Error> list = new ArrayList<Error>();
				list = Error.dao.find("select * from sys_error where question_id = ? and user_id = ?", questionid, userid);
				if (list.size() <= 0) {
					Error error = new Error();
					error.setId(CGUtil.createUUid());
					error.setQuestionId(questionid);
					error.setUserId(userid);
					error.setRecourseId(examid);
					error.setRecourseType(1);
					error.setUpdateBy(userid);
					error.setUpdateDate(new Date());
					error.setCreateDate(new Date());
					error.setCreatorId(userid);
					error.setIsenable(1);
					error.setIsdel(0);
					error.save();
				}
			}
		}
	}

}
