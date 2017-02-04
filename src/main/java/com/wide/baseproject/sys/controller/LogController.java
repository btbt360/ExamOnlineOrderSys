package com.wide.baseproject.sys.controller;

import java.util.List;

import com.wide.common.model.Log;
import com.wide.common.model.query.QueryLog;
import com.wide.viewmodel.DataTablesModel;
import com.wide.baseproject.sys.service.LogService;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

public class LogController extends Controller {
	private static final LogService logService = Enhancer.enhance(LogService.class);

	/**
	 * @author phm 获取全部日志
	 */
	public void add() {
		 @SuppressWarnings("unused")
		String id=getPara("id");
		 List<Log> listlog = logService.getAlllog();
		 setAttr("listlog", listlog);
		render("Log.jsp");
	}

	/**
	 * @author phm Datetables分页显示
	 */
	public void loglist() {
		QueryLog queryLog = new QueryLog();
		queryLog.setLogName(getPara("logname"));
		queryLog.setStarttimes(getPara("starttimes"));
		queryLog.setEndtimes(getPara("endtimes"));
		@SuppressWarnings("rawtypes")
		DataTablesModel logpage = logService.getPageLog(getParaToInt("page").intValue(), getParaToInt("rp").intValue(),
				queryLog);
		this.renderJson(logpage);
	}
	
	/**
	 * @author phm 删除日志
	 */
	public void delLog() {
		String id = getPara("resultids");
		String[] strings = id.split(",");
		try {
			if (strings.length > 0) {
				for (int i = 0; i < strings.length; i++) {
					Db.update("delete from sys_log where id='" + strings[i] + "'");
				}
			}
			renderJson("2");
		} catch (Exception e) {
			renderJson("1");
			e.printStackTrace();
		}
	}
}
