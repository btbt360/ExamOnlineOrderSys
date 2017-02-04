package com.wide.baseproject.sys.controller;

import java.util.List;

import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.wide.baseproject.sys.service.RightService;
import com.wide.viewmodel.ViewTree;

public class RightController extends Controller {
	private static final RightService rightService = Enhancer.enhance(RightService.class);
	
	 /**
	  * @author cg
	  * 查询菜单树
	  * */
	 public void getRightTree(){
		 String id = getPara("id");
		 String roleid = getPara("roleid");
		 id = id==null?"":id;
		 roleid = roleid==null?"":roleid;
		 List<ViewTree> list = rightService.getRightTreeByPid(id,roleid);
		 renderJson(list);
	 }

}
