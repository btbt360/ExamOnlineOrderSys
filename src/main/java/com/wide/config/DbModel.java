package com.wide.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

@SuppressWarnings({ "rawtypes" })
public abstract class DbModel<T extends DbModel> extends Model<T> {
	
    private static final long serialVersionUID = -6215428115177000482L;
 
    private static final TableMapping tableInfoMapping = TableMapping.me();
 
 
    /**
     * 用来针对DataTables封装的分页查询
     * 
     * @param pageNumber
     * @param pageSize
     * @param select
     * @param sqlExceptSelect
     * @param paras
     * @return
     */
    @SuppressWarnings("unchecked")
    public  DataTablesModel paginateDataTables(int pageNumber, int pageSize,
            String select, String sqlExceptSelect, Object... paras) {
        Page<T> pages = super.paginate(pageNumber, pageSize, select.toString(),
                sqlExceptSelect.toString(), paras);
       // List<String> plist= getSelectToList(select);
        Table tInfo = tableInfoMapping.getTable(getClass());
        String[] strarr = tInfo.getPrimaryKey();
        Map<String, Class<?>> maps = tInfo.getColumnTypeMap();
        List<String> ids = new ArrayList<String>();
        List<List<String>> cells = new ArrayList<List<String>>();
        for (int i = 0; i < pages.getList().size(); i++) {
        	List<String> plist = new ArrayList<String>();
            T t = pages.getList().get(i);
            Map<String,String> attr = t.getAttrs();
            Map<String,String> attrs = this.getSelectToList(attr,select, maps);
            int j = -1;
            for (String it : attrs.keySet()) {
                	if(strarr.length>0){
                		if (it.toLowerCase().equals(strarr[0].toLowerCase())) {
                			ids.add(attrs.get(it.trim()).toString());                			
                		}else{
                			j=j+1;
                			if (null != attrs.get(it)) {
                				String ssssssswsString = attrs.get(it);
                				plist.add(j, ssssssswsString);
                			}else{
                				plist.add(j,"");
                			}
                		}
                		
                } 
            }
            cells.add(plist);
        }
 
        return new DataTablesModel(pageNumber, pageSize, pages.getTotalRow(),
                ids, cells);
    }
 
    /**
     * 用来针对DataTables封装的分页查询
     * 
     * @param pageNumber
     * @param pageSize
     * @param select
     * @param sqlExceptSelect
     * @return
     */
    public DataTablesModel paginateDataTables(int pageNumber, int pageSize,
            String select, String sqlExceptSelect) {
        return this.paginateDataTables(pageNumber, pageSize, select,
                sqlExceptSelect, new Object[0]);
    }
 
    private  Map<String,String> getSelectToList(Map<String,String> attrs,String select, Map<String, Class<?>> maps){
    	select = select.replace("select", "");
    	String[] rps = select.split(",");
    	List<String> list = new ArrayList<String>();
    	Map<String,String> map  = new LinkedHashMap<String,String>();
    	if(rps.length>0){
    		for(String s: rps){
    			if(s.indexOf(".")!=-1){
    				String sssew = s.split("[.]")[1];
    				list.add(sssew);    				
    			}else{
    				list.add(s);
    			}
    		}
    	}
    	if(list.size()>0){
    		for(String s:list){
    			map.put(s, getObjToString(maps.get(s.trim()),attrs.get(s.trim()))==null
    					||getObjToString(maps.get(s.trim()),attrs.get(s.trim())).equals("null")?"":getObjToString(maps.get(s.trim()),attrs.get(s.trim())));
    		}
    	}
    	return map;
    }
 
    public String getObjToString(Class<?> res,Object o){
    	String str = o+"";
    	if(res!=null&&!res.equals("")&&res.equals(java.sql.Timestamp.class)){
    		Timestamp t = (Timestamp) o;
    		str=DateUtil.timestamp2Str(t);
    	}
		return str;
    }
    

}