package com.wide.baseproject.sys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.wide.baseproject.sys.service.MenuService;
import com.wide.baseproject.sys.service.UserService;
import com.wide.common.model.Menu;
import com.wide.common.model.Right;
import com.wide.common.model.User;
import com.wide.config.UserToken;
import com.wide.util.CGUtil;
import com.wide.validator.MenuValidator;
import com.wide.viewmodel.ViewTree;

public class MenuController extends Controller
{
	private static final MenuService menuService = Enhancer.enhance(MenuService.class);
	private static final UserService userService = Enhancer.enhance(UserService.class);

	/**
	 * @author cg 获取菜单
	 * 
	 */
	public void getMenuAll()
	{
		List<Menu> listm = menuService.getMenuAll();
	}

	/**
	 * @author cg 菜单管理
	 */
	public void add()
	{
		List<Menu> listm = menuService.getMenuAll();
		setAttr("listmenu", listm);
		render("menuList.jsp");
	}

	/**
	 * @author cg 添加菜单
	 */
	public void addmenuinfo()
	{
		String id = getPara("id");
		Menu menu, pmenu = new Menu();
		menu = menuService.getMenuById(id == null ? "" : id);
		menu = menu == null || menu.equals("") ? new Menu() : menu;
		pmenu = menuService.getMenuById(menu.getParentId() == null ? "" : menu.getParentId());
		setAttr("menu", menu);
		setAttr("pmenu", pmenu);
		setAttr("message",getPara("message"));
		render("menuinfo.jsp");
	}

	/**
	 * 保存菜单
	 */
	@Before(MenuValidator.class)
	public void menuSave()
	{
		Menu menu = getModel(Menu.class);
		if (menu.getId() == null) // 如果不存在ID值就是新增的地区，走添加方法
		{
			Menu menup = menuService.getMenuById(menu.getParentId());
			if (menup == null || menup.getSort().equals(""))
			{
				menup = new Menu();
			}
			HttpSession session = getSession(); // 获取session
			UserToken ut = (UserToken) session.getAttribute("userToken"); // 获取到用户token
			User user = userService.getUser(ut.getVuser().getUser()); // 获取用户
			/*---------------后台赋值--------------*/
			menu.setId(CGUtil.createUUid());
			menu.setCreateBy(user.getId());
			menu.setCreateDate(new Date());
			menu.setUpdateBy(user.getId());
			menu.setUpdateDate(new Date());
			menu.setParentId(menu.getParentId() == null ? "" : menu.getParentId());
			menu.setParentIds(menu.getParentId() == "" ? "" : (menu.getParentId() + "|" + menu.getId()));
			String maxsort = menuService.findMaxSort(menu.getParentId());
			menu.setSort(CGUtil.createSort(menup.getSort() == null || menup.getSort().equals("") ? 0.0 : menup.getSort(),
					Double.parseDouble(maxsort == null || maxsort.equals("") ? "0" : maxsort)));
			/*---------------赋值结束--------------*/
			menu.save();
			setAttr("menu", menu);
			setAttr("message", "success");
			redirect("/menu/add?message=success", true);
		} else
		{ // else走修改方法
			HttpSession session = getSession(); // 获取session
			UserToken ut = (UserToken) session.getAttribute("userToken"); // 获取到用户token
			User user = userService.getUser(ut.getVuser().getUser()); // 获取用户
			menu.setParentId(menu.getParentId() == null ? "" : menu.getParentId());
			menu.setUpdateBy(user.getId()); // 修改人为当前用户
			menu.setUpdateDate(new Date()); // 修改日期为当前时间
			menu.update(); // menu.update()方法
			setAttr("message", "success");
			redirect("/menu/add?message=success", true);
		}
	}

	/**
	 * @author cg 删除菜单
	 */
	public void delmenuinfo()
	{
		String id = getPara("id");
		String num = menuService.countChild(id);
		if (Integer.parseInt(num) == 0)
		{
			setAttr("message", "success");
			render("menuList.jsp");
			menuService.delmeun(id);
		} else
		{
			setAttr("message", "error");
			render("menuList.jsp");
		}
		List<Menu> listm = menuService.getMenuAll();
		setAttr("listmenu", listm);
		render("menuList.jsp");
	}

	/**
	 * @author cg 查询菜单树
	 */
	public void getMenuTree()
	{
		String id = getPara("id");
		String menuid = getPara("menuid");
		id = id == null ? "" : id;
		List<ViewTree> list = menuService.getMenuTreeByPid(id, menuid);
		renderJson(list);
	}

	/**
	 * 菜单权限同步
	 */
	public void menusynchronization()
	{
		HttpSession session = getSession(); // 获取session
		UserToken ut = (UserToken) session.getAttribute("userToken"); // 获取到用户token
		User user = userService.getUser(ut.getVuser().getUser()); // 获取用户
		List<Menu> listm = menuService.getMenuAll();
		try
		{
			if (listm.size() > 0)
			{
				for (Menu menu : listm)
				{
					Right right = menuService.getRightBymenuId(menu.getId());
					right.setUpdateBy(user.getId());
					right.setUpdateDate(new Date());
					if (right.getId() != null && !right.getId().equals(""))
					{
						right.setResourcesname(menu.getName());
						right.setPermission(menu.getPermission());
						right.update();
					} else
					{
						right.setResourcesid(menu.getId());
						right.setResourcesname(menu.getName());
						right.setResourcestype(1);
						right.setPermission(menu.getPermission());
						right.setDelFlag("0");
						right.setId(CGUtil.createUUid());
						right.setCreateBy(user.getId());
						right.setCreateDate(new Date());
						right.save();
					}
				}
				renderJson("1");

			}
		} catch (Exception e)
		{
			renderJson("2");
			e.printStackTrace();
		}
	}
}
