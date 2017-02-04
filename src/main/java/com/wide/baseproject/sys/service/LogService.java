package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.common.model.Dict;
import com.wide.common.model.Log;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryLog;
import com.wide.util.CGUtil;
import com.wide.util.DateUtil;
import com.wide.viewmodel.DataTablesModel;


public class LogService {
	/**
	 * @author phm 获取全部日志
	 */
	public List<Log> getAlllog(){
		List<Log> loglist = Log.dao.getAllLog();
		return loglist;
	}
	
	/**
	 * @author phm Datetables分页显示
	 */
	public DataTablesModel getPageLog(int pageNum, int pageSize, QueryLog queryLog) {
		DataTablesModel logpage = Log.dao.pageDataTables(pageNum, pageSize, queryLog);
		List<String> ids = new ArrayList<String>();
		if (logpage != null && !logpage.equals("")) {
			List<List<String>> rows = logpage.getRows();
			if (rows.size() > 0) {
				for (int i = 0; i < rows.size(); i++) {
					List<String> row = rows.get(i);
					ids.add(row.get(0));
					row.set(1, EnumOptType.getFromKey(Integer.parseInt(row.get(1))));
					row.add(6, "<a href='#' onclick=del('" + row.get(0).trim() + "') >删除</a>");
					row.remove(0);
				}
			}
		}
		logpage.setIds(ids);
		return logpage;
	}
	
	/**
	 * @author phm
	 * 日志保存
	 * @param Integer optype 操作类型  来自枚举 EnumOptType
	 * @param Integer fntype 功能类型  来自枚举 EnumFuncType
	 * @param User user 用户对象
	 * @return boolean 保存是否成功
	 * */
	public boolean saveLog(Integer optype,Integer fntype,User user){		
		optype = optype==null||optype.equals("")?0:optype;   
		fntype = fntype==null||fntype.equals("")?0:fntype;
		String opname = Dict.dao.getDictByKeyType(optype.toString(), "1011");
		String fnname = Dict.dao.getDictByKeyType(fntype.toString(), "1012");
		 opname = opname==null?"":EnumOptType.getFromKey(optype);
		 fnname = fnname==null?"":EnumFuncType.getFromKey(fntype);
		Log log = new Log();
		String content =user.getName()+"在"+DateUtil.toDateTimeStr(new Date())+"操作了"+fnname+opname+"功能" ;
		if(user!=null&&!user.equals("")){
			log.setId(CGUtil.createUUid());
			log.setContent(content);
			log.setTitle(fnname+opname);
			log.setType(optype.toString());
			log.setOperatorname(user.getName());
			log.setCreateBy(user.getId());
			log.setCreateDate(new Date());
			log.save();
			return true;
		}
		return false;
	}
}
