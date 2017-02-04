package com.wide.baseproject.sys.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;


import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.common.model.Dict;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryDict;
import com.wide.viewmodel.DataTablesModel;
import com.wide.base.BaseController;
import com.wide.base.RenturnInfo;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.LogService;
import com.wide.baseproject.sys.service.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class DictController extends BaseController {

	private static final DictService dictService = Enhancer
			.enhance(DictService.class);
	private static final UserService userService = Enhancer
			.enhance(UserService.class);
	private static final LogService logService = Enhancer
			.enhance(LogService.class);

	public static DictService getDictservice() {
		return dictService;
	}

	public static UserService getUserservice() {
		return userService;
	}

	public static LogService getLogservice() {
		return logService;
	}

	/**
	 * 跳转列表页
	 */
	//@RequiresPermissions("dict:add")
	public void add() {
		render("dictlist.jsp");

	}

	/**
	 * 获取当前用户
	 * 
	 * @return User
	 */
	public User getCurrentUser() {
		HttpSession session = getSession(); // 获取当前用户
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());

		return user;

	}

	/**
	 * 保存
	 */
	// @Before(DictValidator.class)
	public void save() {
		Dict dict = getModel(Dict.class);

		try {
			if (dict == null) {
				setAttr("messagefalse", "false");// 获取表单数据失败
				redirect("/dict/findlist");
			} else {
				dict.setUpdateBy(getCurrentUser().getUpdateBy());
				dict.setUpdateDate(new Timestamp(System.currentTimeMillis()));
				if (dict.getId() == null) {
					dict.setId(UUID.randomUUID().toString());
					dict.setCreateBy(getCurrentUser().getCreateBy());
					dict.setCreateDate(new Timestamp(System.currentTimeMillis())); // 补充修改人，创建人信息
					dict.save();
					logService.saveLog(EnumOptType.add.getEnumKey(),
							EnumFuncType.dict.getEnumKey(), getCurrentUser()); // 数据字典添加日志保存
				} else {
					dict.update();
					logService.saveLog(EnumOptType.edit.getEnumKey(),
							EnumFuncType.dict.getEnumKey(), getCurrentUser()); // 数据字典修改日志保存
				}
				setAttr("dict", dict);
				setAttr("message", "success");
				render("dictinfo.jsp");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 跳转详情页
	 */
	public void adddictinfo() {
		String id = getPara("id");
		Dict dict = dictService.getDictById(id);
		setAttr("dict", dict);
		render("dictinfo.jsp");
	}

	/**
	 * 删除
	 */
	public void delete() {
		returninfo = new RenturnInfo();
		String id = getPara("id");
		try{
			if(id!=null&&!id.equals("")){
				Db.update("update sys_dict set del_flag = 1 where id = ? ",id);
			}
			logService.saveLog(EnumOptType.del.getEnumKey(),
					EnumFuncType.dict.getEnumKey(), getCurrentUser()); // 数据字典删除日志保存
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
	 * 字典树列表查询
	 */
	public void findlist() {
		QueryDict queryDict = new QueryDict();
		queryDict.setDictname(getPara("dictname"));
		queryDict.setDicttype(getPara("dicttype"));
		queryDict.setStarttimes(getPara("starttimes"));
		queryDict.setEndtimes(getPara("endtimes"));
		DataTablesModel dictpage = dictService.getPageDict(getParaToInt("page")
				.intValue(), getParaToInt("rp").intValue(), queryDict);
		this.renderJson(dictpage);
	}

	/***
	 * 数据字典key重复验证
	 */
	@Clear
	public void dictkeycheck() {
		String dictkey = getPara("dictkey");
		String type = getPara("type");
		String code = "0";
		try {
			if (dictkey.equals("") != true && type.equals("") != true) {
				String sql = "select * from sys_dict where dictkey = '"
						+ dictkey + "' and type = '" + type
						+ "'   and del_flag ='0' ";
				List<Record> userlist = new ArrayList<Record>();
				userlist = Db.find(sql);
				if (userlist.size() > 0) {
					code = "1";
				} 
					renderJson(code);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
