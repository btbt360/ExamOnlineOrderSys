package com.wide.validator;

import com.wide.common.model.Dict;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class DictValidator extends Validator {

	@Override
	protected void validate(Controller c) {	
		validateRequiredString("dict.dictvalue", "dictnameMsg", "请输入数据字典名称！");
		validateRequiredString("dict.dictkey", "dictkeyMsg", "请输入数据字典键值！");
		if (c.getPara("dict.type").equals("0")) { // 自定义的数据字典类型验证方法
			addError("dicttypeMsg", "请选择数据字典类型！");
		}		
	}

	@Override
	protected void handleError(Controller c) {		
		c.keepModel(Dict.class);
		c.setAttr("testid","0");
		c.render("dictinfo.jsp");		
	}

}
