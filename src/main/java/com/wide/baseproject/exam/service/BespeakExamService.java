package com.wide.baseproject.exam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.wide.common.model.Bespeakexam;
import com.wide.common.model.BespeakexamExaminee;
import com.wide.common.model.Error;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryBespeak;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.wide.viewmodel.ViewCalendar;

public class BespeakExamService {

	public DataTablesModel getPageBespeak(int pageNum, int pageSize, QueryBespeak qb) {
		// TODO Auto-generated method stub
		DataTablesModel bespeakpage = Bespeakexam.dao.pageDataTables(pageNum, pageSize, qb);
		if (bespeakpage != null && !bespeakpage.equals("")) {
			List<List<String>> rows = bespeakpage.getRows();
			if (rows.size() > 0) {
			for (int i = 0; i < rows.size(); i++) {
				List<String> row = rows.get(i);
				String id = row.get(0).trim();
				row.set(0, row.get(1));
				row.set(1, row.get(2));
				row.set(2, row.get(3));
				row.set(3, row.get(4));
				row.set(4, row.get(5));
				row.set(5, "");
				row.set(6, Integer.parseInt((row.get(6) + "")) == 1 ? "<font color='#00ff66'>启用</font>" : "<font color='#C9C9C9'>禁用</font>");
				row.set(7, "");
				if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))>0){
					row.set(5, "<span class='label'>考试已结束</span>");
					if(StrKit.notBlank(qb.getUserid())){
						row.set(7, "<font color = 'green'>已预约</font>");
					}else{
						row.set(7, "");
					}
				}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))<0 && (DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))>=0){
					row.set(5, "<span class='label label-success'>考试正在进行中</span>");
					if(StrKit.notBlank(qb.getUserid())){
						row.set(7, "<font color = 'green'>已预约</font>");
					}else{
						row.set(7, "");
					}
				}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))<0){
					row.set(5, "<span class='label label-info'>可以预约考试</span>");
					if(StrKit.notBlank(qb.getUserid())){
						row.set(7, "<font color = 'green'>已预约</font>");
					}else{
						row.set(7, "<a href ='#' onclick=edit('" + id
								+ "') >修改</a> | <a href='#' onclick=del('" + id + "') >删除</a>"  );
					}
				 }
			   }
			}
		}
		return bespeakpage;
		
	}

	public List<ViewCalendar> usersubscribe(User user) throws Exception{
		// TODO Auto-generated method stub
		List<ViewCalendar> vclist = new ArrayList<ViewCalendar>();
		List<Bespeakexam> blist = new ArrayList<Bespeakexam>();
		blist = Bespeakexam.dao.find("select * from sys_bespeakexam where isenable = 1 and isdel = 0 ");
		if(blist.size()>0){
			for(Bespeakexam be:blist){
				ViewCalendar vc = new ViewCalendar();
				vc.setId(be.getId());
				List<BespeakexamExaminee> listbee = getBeSList(be.getId(),null);
				List<BespeakexamExaminee> listbeu = getBeSList(be.getId(),user.getId());
				vc.setTitle("    "+be.getExamname()+"："+(listbeu.size()>0?"已预约":((be.getNumber()-listbee.size())+"人剩余")));
				vc.setStart(DateUtil.toDateTimeStr(be.getStarttime()));
				vc.setEnd(DateUtil.toDateTimeStr(be.getEndtime()));
				vc.setColor(listbeu.size()>0?"gray":"");
				vc.setClassName("");
				vc.setTextColor("");
				vclist.add(vc);
			}
		}
		return vclist;
	}

	public List<BespeakexamExaminee> getBeSList(String examid,String userid){
		List<BespeakexamExaminee> listbee = new ArrayList<BespeakexamExaminee>();
		String sql = "select * from sys_bespeakexam_examinee where 1=1 ";
		String sqlWhere = "";
		if(StrKit.notBlank(examid)){
			sqlWhere = sqlWhere+" and exam_id = '"+examid+"'";
		}
		if(StrKit.notBlank(userid)){
			sqlWhere = sqlWhere+" and examinee_id = '"+userid+"'";
		}
		listbee = BespeakexamExaminee.dao.find(sql+sqlWhere);
		return listbee;
	}

	public DataTablesModel getPageBespeakQuery(int pageNum, int pageSize, QueryBespeak qb) {
		// TODO Auto-generated method stub
		DataTablesModel bespeakpage = Bespeakexam.dao.pageDataTables(pageNum, pageSize, qb);
		if (bespeakpage != null && !bespeakpage.equals("")) {
			List<List<String>> rows = bespeakpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					String id = row.get(0).trim();
					row.set(0, row.get(1));
					row.set(1, row.get(2));
					row.set(2, row.get(3));
					row.set(3, row.get(4));
					row.set(4, row.get(5));
					List<BespeakexamExaminee> listbee = getBeSList(id,null);
					row.set(5, listbee.size()+"");
					String names ="";
					if(listbee.size()>0){
						for(BespeakexamExaminee be:listbee){
							User user = new User();
							user = User.dao.findById(be.getExamineeId());
							names = names + "<a href='#' onclick = checkuserinfo('"+user.getId()+"') >"+user.getName() +"</a> | ";
						}
					}
					row.set(6, names);
					row.set(7, "");
					if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))>0){
						row.set(7, "<span class='label'>考试已结束</span>");
					}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(3)))<0 && (DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))>=0){
						row.set(7, "<span class='label label-success'>考试正在进行中</span>");
					}else if((DateUtil.compare_date(DateUtil.toDateTimeStr(new Date()), row.get(2)))<0){
						row.set(7, "<span class='label label-info'>可以预约考试</span>");
					}
				}
			}
		}
		return bespeakpage;
	}
}
