package com.wide.baseproject.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.wide.config.UserToken;
import com.wide.constant.EnumFuncType;
import com.wide.constant.EnumOptType;
import com.wide.common.model.Area;
import com.wide.common.model.Dict;
import com.wide.common.model.User;
import com.wide.common.model.query.QueryArea;
import com.wide.util.CGUtil;
import com.wide.viewmodel.ViewTree;
import com.wide.baseproject.sys.service.AreaService;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.LogService;
import com.wide.baseproject.sys.service.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class AreaController extends Controller
{
	private static final AreaService areaService = Enhancer.enhance(AreaService.class);
	private static final UserService userService = Enhancer.enhance(UserService.class);
	private static final DictService dictService = Enhancer.enhance(DictService.class);
	private static final LogService logService = Enhancer.enhance(LogService.class);
	

	public static AreaService getAreaservice() {
		return areaService;
	}

	public static UserService getUserservice() {
		return userService;
	}

	public static DictService getDictservice() {
		return dictService;
	}

	public static LogService getLogservice() {
		return logService;
	}

	/**
	 * 获取当前用户
	 * @return User
	 */
	public User getCurrentUser(){
		HttpSession session = getSession();          //获取当前用户
		UserToken ut = (UserToken) session.getAttribute("userToken");
		User user = userService.getUser(ut.getVuser().getUser());
		
		return user;
		
	}
	/**
	 * 获取地区
	 * 
	 */
	public void getAreaAll()
	{
		@SuppressWarnings({ "unused", "static-access" })
		List<Area> lista = areaService.getAreaAll(); // 获取所有菜单
	}

	/**
	 * 地区管理
	 */
	public void add()
	{
		QueryArea queryArea = new QueryArea();
		String areatype = getPara("areatype");
		queryArea.setAreatype(areatype == null || areatype.equals("") ? 0 : Integer.parseInt(areatype));
		queryArea.setEndtime(getPara("endtime"));
		queryArea.setStarttime(getPara("starttime"));
		queryArea.setAreaname(getPara("areaname"));
		@SuppressWarnings("static-access")
		List<Area> lista = areaService.getAreasByQuery(queryArea);
		List<Dict> listd = dictService.getDictByType("1010");
		setAttr("areatype", areatype);
		setAttr("endtime", getPara("endtime"));
		setAttr("starttime", getPara("starttime"));
		setAttr("areaname", getPara("areaname"));
		setAttr("listarea", lista);
		setAttr("listdict", listd);
		render("areaList.jsp");
	}

	/**
	 * 添加地区
	 */
	public void addareainfo()
	{
		String id = getPara("id"); // 接收id
		Area area, parea = new Area(); // 实例化area，pare
		area = areaService.getAreaById(id == null ? "" : id); // 通过ID获得
		area = area == null || area.equals("") ? new Area() : area;
		parea = areaService.getAreaById(area.getParentId() == null ? "" : area.getParentId());
		List<Dict> listd = new ArrayList<Dict>(); // 初始化listd(装载查询出来的数据)
		listd = dictService.getDictByType("1010"); // 通过type值查询该type值所有的数据
		// 向前台传值
		setAttr("listdict", listd);
		setAttr("area", area);
		setAttr("parea", parea);
		String mark = getPara("message");
		setAttr("message", mark);
		render("areainfo.jsp");
	}
	
	@Clear
	public void areacheck(){
		String areacode = getPara("areacode");
		if(areacode.equals("")!=true){
			String sql = "select * from sys_area where code = '"+areacode+"' and del_flag = '0'";
			List<Record> list = Db.query(sql);
			if(list.size() > 0){
				renderJson(false);
			}else{
				renderJson(true);
			}
		}
	}
	
	/**
	 * 保存地区
	 */
	//@Before(AreaValidator.class)
	public void areaSave()
	{
		  Area area = getModel(Area.class); // 从前台页面取值
		  area.setParentId(area.getParentId() == null ? "" : area.getParentId());		
		if (area.getId() == null) // 如果不存在ID值就是新增的地区，走添加方法
		{
			Area areap = areaService.getAreaById(area.getParentId());
			if (areap == null || areap.getSort().equals(""))
			{
				areap = new Area();
			}			
			/*---------------后台赋值--------------*/
			area.setId(CGUtil.createUUid());
			area.setCreateBy(getCurrentUser().getId());
			area.setCreateDate(new Date());
			area.setUpdateBy(getCurrentUser().getId());
			area.setUpdateDate(new Date());			
			area.setParentIds(area.getParentId() == "" ? "" : (area.getParentId() + "|" + area.getId()));
			String maxsort = areaService.findMaxSort(area.getParentId());
			area.setSort(
					CGUtil.createSort(areap.getSort() == null || areap.getSort().equals("") ? 0.0 : areap.getSort(),
							Double.parseDouble(maxsort == null || maxsort.equals("") ? "0" : maxsort)));
			/*---------------赋值结束--------------*/
			area.save();
			logService.saveLog(EnumOptType.add.getEnumKey(), EnumFuncType.area.getEnumKey(), getCurrentUser()); //地区添加日志保存
			setAttr("area", area);
		
		} else
		{ // else走修改方法				
			area.setUpdateBy(getCurrentUser().getId()); // 修改人为当前用户
			area.setUpdateDate(new Date()); // 修改日期为当前时间
			area.update(); // area.update()方法
			logService.saveLog(EnumOptType.edit.getEnumKey(), EnumFuncType.area.getEnumKey(), getCurrentUser()); //地区修改日志保存
		}
		   setAttr("message", "success");
		   keepModel(Area.class);
		   List<Dict> listd = dictService.getDictByType("1010"); // 通过type值查询该type值所有的数据
		    // 向前台传值
		   setAttr("listdict", listd);
		   render("areainfo.jsp");
	}

	/**
	 * @author zb 删除地区
	 */
	public void delareainfo()
	{   
		
		String id = getPara("id");
		String num = areaService.countChild(id);//使用countChild计算出是否有下级地区
		//无下级地区执行删除并返回success，有则返回error
		if (Integer.parseInt(num) == 0)
		{
			setAttr("message", "success");
			redirect("/area/add?message=success", true);
			areaService.delarea(id);
			logService.saveLog(EnumOptType.del.getEnumKey(), EnumFuncType.area.getEnumKey(), getCurrentUser()); //地区删除日志保存
		} else
		{
			setAttr("message", "error");
			redirect("/area/add?message=error", true);
		}
		@SuppressWarnings("static-access")
		List<Area> lista = areaService.getAreaAll();
		setAttr("listarea", lista);
		render("areaList.jsp");
	}

	/**
	 * @author cg 查询菜单树
	 */
	public void getAreaTree()
	{
		String id = getPara("id");
		String areaid = getPara("areaid");
		id = id == null ? "" : id;
		List<ViewTree> list = areaService.getAreaTreeByPid(id, areaid);
		renderJson(list);
	}

}
