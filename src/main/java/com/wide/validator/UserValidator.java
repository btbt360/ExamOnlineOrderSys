package com.wide.validator;

import java.util.List;

import com.wide.common.model.Dict;
import com.wide.common.model.Role;
import com.wide.common.model.User;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.RoleService;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class UserValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("user.name", "usernameMsg", "请输入用户姓名！");
		validateRequiredString("user.loginName", "loginNameMsg", "请输入登录名！");
		validateRequiredString("user.email", "noemailMsg", "请输入用户邮箱！");
		validateRequiredString("user.mobile", "mobileMsg", "请填写用户手机!");
		validateEmail("user.email", "noemailMsg", "请正确填写用户邮箱!");
		if (controller.getPara("usertype").equals("0")) { // 自定义的用户类型验证方法
			addError("userTypeMsg", "请选择用户类型！");
		}
	}

	protected void handleError(Controller controller) {
		controller.keepModel(User.class);
		DictService dictService = controller.enhance(DictService.class);
		List<Dict> listd = dictService.getDictByType("1003");
		RoleService roleService = controller.enhance(RoleService.class);
		List<Role> rolelist = roleService.getRoleAll();
		controller.setAttr("utype", controller.getPara("usertype"));
		controller.setAttr("roleids", controller.getPara("roleids"));
		controller.setAttr("rolelist", rolelist);
		controller.setAttr("listdict", listd);
		controller.render("userInfo.jsp");
	}
}
