package com.wide.route;

import com.jfinal.config.Routes;
import com.wide.baseproject.resource.controller.CaseController;
import com.wide.baseproject.resource.controller.ItemBankController;
import com.wide.baseproject.resource.controller.QuestionsController;
import com.wide.baseproject.resource.controller.SubjectController;

public class ResourceRoutes extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/subject",SubjectController.class,"/pages/resource/");
		add("/case",CaseController.class,"/pages/resource/");
		add("/item",ItemBankController.class,"/pages/resource/");
		add("/questions",QuestionsController.class,"/pages/resource/");
	}

}
