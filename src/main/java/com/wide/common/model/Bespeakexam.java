package com.wide.common.model;

import com.jfinal.kit.StrKit;
import com.wide.common.model.base.BaseBespeakexam;
import com.wide.common.model.query.QueryBespeak;
import com.wide.common.model.query.QueryError;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Bespeakexam extends BaseBespeakexam<Bespeakexam> {
	public static final Bespeakexam dao = new Bespeakexam();

	public DataTablesModel pageDataTables(int pageNum, int pageSize, QueryBespeak qb) {
		// TODO Auto-generated method stub
		String select = "select id,examname,examcode,starttime,endtime,number,isenable,status";
	    StringBuilder sqlExceptSelect = new StringBuilder(" from sys_bespeakexam");
	    /**
	    if (search!=null&&!search.equals("")) {
	        sqlExceptSelect.append(" AND (b.title like ? or b.content like ? )");
	        parameters.add("%" + search + "%");
	        parameters.add("%" + search + "%");
	    } 
	     **/
	    sqlExceptSelect.append(whereQuery(qb));
	    sqlExceptSelect.append(orderbyQuery(qb));
	    
	    return this.paginateDataTables(pageNum, pageSize, select, sqlExceptSelect.toString());
	}
	/**
	 * query where查询
	 * 
	 * */
	private String whereQuery(QueryBespeak qb){
		String where=" where 1=1 and isdel = 0 ";
		
		if(qb.getName()!=null&&!qb.getName().equals("")){
			where += " and name like '%"+qb.getName()+"%'";
		}
		if(qb.getStarttime()!=null&&!qb.getStarttime().equals("")){
			where  +=" and starttime > '"+qb.getStarttime()+"'";
		}
		if(qb.getEndtime()!=null&&!qb.getEndtime().equals("")){
			where  +=" and endtime < '"+qb.getEndtime()+"'";
		}
		if(StrKit.notBlank(qb.getUserid())){
			where  +=" and id in (select distinct exam_id from sys_bespeakexam_examinee where examinee_id='"+qb.getUserid()+"')";
		}
		return where;
		
	}
	/**
	 * query order by 
	 * 
	 * */
	private String orderbyQuery(QueryBespeak qb){
		String orderby = " order by create_date desc ";
		return orderby;
		
	}
	
	
}
