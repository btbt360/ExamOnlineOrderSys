package com.wide.validator;

import com.wide.common.model.Area;
import com.wide.common.model.Office;
import com.wide.baseproject.sys.service.AreaService;
import com.wide.baseproject.sys.service.DictService;
import com.wide.baseproject.sys.service.OfficeService;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class OfficeValidator extends Validator{
	private static final DictService dictService = Enhancer.enhance(DictService.class);
	private static final OfficeService officeService = Enhancer.enhance(OfficeService.class);
	private static final AreaService areaService = Enhancer.enhance(AreaService.class);
	protected void validate(Controller controller) {
		validateRequiredString("office.name", "nameMsg", "请填写机构名称!");
		validateRequiredString("office.code", "codeMsg", "请填写机构编码!");
		validateRequiredString("office.master", "masterMsg", "请填写负责人姓名!");
		
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Office.class);
		Office poffice = new Office();
		Office parentOffice = new Office();
		Office office = new Office();
		Area area = new Area();
		String type = controller.getPara("office.type");
		String id = controller.getPara("office.id");
		if (id != null && !id.equals("")) {
			office = officeService.getOfficeByid(id);
			parentOffice = officeService.getOfficeByid(office.getParentId());
			if (parentOffice == null || parentOffice.equals("")) {
				parentOffice = new Office();
				parentOffice.setName("");
			}
			area = areaService.getAreaById(office.getAreaId());
		}
		String lastpname = poffice.getName() != null && !poffice.getName().equals("") ? poffice.getName()
				: parentOffice.getName();
		String areaname = area == null ? "" : area.getName() == null ? "" : area.getName();
		String areaid = area == null ? "" : area.getId() == null ? "" : area.getId();
		String typename = dictService.getDictByKeyType(type, "1000");
		controller.setAttr("typename", typename);
		controller.setAttr("lastpname", lastpname); 
		controller.setAttr("areaname", areaname); 
		controller.setAttr("areaid", areaid); 
		controller.render("officeInfo.jsp");
	}
}
