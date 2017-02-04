package com.wide.route;

import com.wide.baseproject.sys.controller.AreaController;
import com.wide.baseproject.sys.controller.DictController;
import com.wide.baseproject.sys.controller.FileController;
import com.wide.baseproject.sys.controller.LogController;
import com.wide.baseproject.sys.controller.LoginController;
import com.wide.baseproject.sys.controller.MenuController;
import com.wide.baseproject.sys.controller.OfficeController;
import com.wide.baseproject.sys.controller.RightController;
import com.wide.baseproject.sys.controller.RoleController;
import com.wide.baseproject.sys.controller.UserController;
import com.jfinal.config.Routes;

public class SysRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/", LoginController.class,"/pages/sys");
		add("/user", UserController.class,"/pages/sys");
		add("/menu", MenuController.class,"/pages/sys");
		add("/role", RoleController.class,"/pages/sys");
		add("/area", AreaController.class,"/pages/sys");
		add("/dict", DictController.class,"/pages/sys");
		add("/office", OfficeController.class,"/pages/sys");
		add("/right", RightController.class,"/pages/sys");
		add("/log",LogController.class,"/pages/sys");
		//文件管理
		add("/file", FileController.class,"/pages/sys");
	


	}

}