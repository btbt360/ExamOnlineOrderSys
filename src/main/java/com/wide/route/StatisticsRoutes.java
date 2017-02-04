package com.wide.route;

import com.jfinal.config.Routes;
import com.wide.baseproject.exam.controller.ExamController;
import com.wide.baseproject.exam.controller.ExamineeController;
import com.wide.baseproject.exam.controller.ExampapersController;
import com.wide.baseproject.exam.controller.InvigilateController;
import com.wide.baseproject.statistics.controller.StatisticsController;
import com.wide.baseproject.sys.controller.LogController;

public class StatisticsRoutes extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/statistics",StatisticsController.class,"/pages/statistics");
	
	}

}
