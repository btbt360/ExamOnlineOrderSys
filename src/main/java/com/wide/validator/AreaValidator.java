package com.wide.validator;

import java.util.List;

import com.wide.common.model.Area;
import com.wide.common.model.Dict;
import com.wide.baseproject.sys.service.DictService;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class AreaValidator extends Validator {
	List<Dict> listd = Enhancer.enhance(DictService.class).getDictByType("1010");

	@Override
	protected void validate(Controller controller) {
		// TODO Auto-generated method stub
		validateRequiredString("area.name", "nameMsg", "请填写地区名称！");
		validateRequiredString("area.code", "codeMsg", "请填写区域编码！");
		validateRequiredString("area.type", "typeMsg", "请选择区域类型！");
	}

	@Override
	protected void handleError(Controller controller) {
		// TODO Auto-generated method stub
		controller.keepModel(Area.class);
		controller.setAttr("listdict", listd);
		controller.render("areainfo.jsp");
	}

}
