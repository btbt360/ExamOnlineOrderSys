package com.wide.route;

import com.jfinal.config.Routes;
import com.wide.baseproject.exam.controller.AchievementController;
import com.wide.baseproject.exam.controller.BespeakExamContrller;
import com.wide.baseproject.exam.controller.ErrorSubjectController;
import com.wide.baseproject.exam.controller.ExamController;
import com.wide.baseproject.exam.controller.ExamineeController;
import com.wide.baseproject.exam.controller.ExampapersController;
import com.wide.baseproject.exam.controller.InvigilateController;

public class ExamRoutes extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/invigilate",InvigilateController.class,"/pages/exam");
		add("/exam",ExamController.class,"/pages/exam/");
		add("/exampapers",ExampapersController.class,"/pages/exam/");
		add("/examinee",ExamineeController.class,"/pages/exam/");
		add("/achievement",AchievementController.class,"/pages/exam/");
		add("/errorsubject",ErrorSubjectController.class,"/pages/exam/");
		add("/bespeak",BespeakExamContrller.class,"/pages/bespeak/");
		
	}

}
