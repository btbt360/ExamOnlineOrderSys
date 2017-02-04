package com.wide.common.model;

import java.util.ArrayList;
import java.util.List;

import com.wide.common.model.base.BaseDict;
import com.wide.common.model.query.QueryDict;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Dict extends BaseDict<Dict> {
	public static final Dict dao = new Dict();
	/**
	 * 全列表查询
	 */
	public List<Dict> getDictAll() {
		List<Dict> lists = find("SELECT * FROM sys_dict WHERE del_flag=0 order by create_date asc");
		return 	 lists ;
	}
	
	
	/**
	 * datatables 查询
	 * @param pageNum
	 * @param pageSize
	 * @param queryDict
	 * @return
	 */
	public DataTablesModel pageDataTables(int pageNum, int pageSize, QueryDict queryDict) {
	    final List<Object> parameters = new ArrayList<Object>();
	    String select = "select id,dictvalue,dictkey,typeinfo,create_date ";
	    StringBuilder sqlExceptSelect = new StringBuilder(" from sys_dict  ");
	    /**
	    if (search!=null&&!search.equals("")) {
	        sqlExceptSelect.append(" AND (b.title like ? or b.content like ? )");
	        parameters.add("%" + search + "%");
	        parameters.add("%" + search + "%");
	    } 
	     **/
	    sqlExceptSelect.append(whereQuery(queryDict));
	    sqlExceptSelect.append(orderbyQuery(queryDict));
	    
	    return this.paginateDataTables(pageNum, pageSize, select.toString(), sqlExceptSelect.toString());
	}
	
	/**
	 * query where查询
	 * 
	 * */
	private String whereQuery(QueryDict query){
		String where=" where 1=1  and del_flag = 0 ";
		if(query.getDictname()!=null&&!query.getDictname().equals("")){
			where += " and dictvalue like '%"+query.getDictname()+"%'";
		}
		if(query.getDicttype()!=null&&!query.getDicttype().equals("")&&!query.getDicttype().equals("0")){
			where  +=" and type = '"+query.getDicttype()+"'";
		}
		if(query.getStarttimes()!=null&&!query.getStarttimes().equals("")){
			where  +=" and create_date >= '"+query.getStarttimes()+" 00:00:00'";
		}
		if(query.getEndtimes()!=null&&!query.getEndtimes().equals("")){
			where  +=" and create_date <=  '"+query.getEndtimes()+" 23:59:59'";
		}
		return where;
		
	}
	/**
	 * query order by 
	 * 
	 * */
	private String orderbyQuery(QueryDict query){
		String orderby = " order by create_date desc ";
		return orderby;
		
	}
    /**
     * 按字典类型,key值查询数据字典
     * @param key
     * @param type
     * @return
     */
	public String getDictByKeyType(String key, String type) {
		// TODO Auto-generated method stub
		List<Dict> list = new ArrayList<Dict>();
		list = find(" select * from sys_dict where dictkey = ? and type = ?  order by create_date asc ",key,type);
		String value="";
		if(list.size()>0){
			value = list.get(0).getDictvalue();
		}
		return value;
	}
	/**
	 * 按字典类型查询
	 * @param type
	 * @return
	 */
	public List<Dict> getDictByType(String type) {
		List<Dict> lists = find("select * from sys_dict where type = ? and del_flag=0  order by dictkey asc ",type);
		return 	 lists ;
	}
	/**
	 * 按字典类型查询对象
	 * @param type
	 * @return
	 */
	public Dict getDictObjBykeyType(String key, String type) {
		List<Dict> list = new ArrayList<Dict>();
		Dict d =new Dict();
		list = find(" select * from sys_dict where dictkey = ? and type = ?  order by create_date asc ",key,type);
		if(list.size()>0){
			d = list.get(0);
		}
		return d ;
	}
   
}