package com.wide.validator;

import java.util.List;

import com.wide.common.model.Dict;
import com.wide.common.model.Role;
import com.wide.baseproject.sys.service.DictService;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class RoleValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("role.name", "rolenameMsg", "请输入角色名称！");
		if (controller.getPara("role.roleType").equals("0")) { // 自定义的角色类型验证方法
			addError("roletypeMsg", "请选择角色类型！");
		}
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Role.class);
		DictService dictService = controller.enhance(DictService.class);
		List<Dict> listd = dictService.getDictByType("1001");
		
		controller.setAttr("vroletype", controller.getPara("role.roleType"));
		controller.setAttr("listd", listd);
		controller.render("roleInfo.jsp");
	}
}
