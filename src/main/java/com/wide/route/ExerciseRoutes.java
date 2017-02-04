package com.wide.route;

import com.jfinal.config.Routes;
import com.wide.baseproject.exam.controller.ExamController;
import com.wide.baseproject.exercise.controller.ExerciseController;

public class ExerciseRoutes extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/exercise",ExerciseController.class,"/pages/exercise/");
	}

}
