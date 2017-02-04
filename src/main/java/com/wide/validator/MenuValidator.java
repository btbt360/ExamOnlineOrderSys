package com.wide.validator;

import com.wide.common.model.Menu;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class MenuValidator extends Validator {

	@Override
	protected void validate(Controller controller) {
		// TODO Auto-generated method stub
		validateRequiredString("menu.name", "nameMsg", "请填写菜单名称！");
		validateRequiredString("menu.href", "hrefMsg", "请填写链接地址！");
		validateRequiredString("menu.isShow", "showMsg", "请选择是否显示！");
	}

	@Override
	protected void handleError(Controller controller) {
		// TODO Auto-generated method stub
		controller.keepModel(Menu.class);
		controller.render("menuinfo.jsp");
	}

}
