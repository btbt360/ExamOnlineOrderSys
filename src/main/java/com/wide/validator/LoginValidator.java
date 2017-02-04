package com.wide.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		validateRequiredString("user.loginName", "loginNameMsg", "请输入用户名！");
		validateRequiredString("user.password", "passwordMsg", "请输入密码！");
		
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.setAttr("error", 1);
		c.render("login.jsp");
		
	}

}
