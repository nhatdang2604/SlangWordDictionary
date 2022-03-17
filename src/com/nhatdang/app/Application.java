package com.nhatdang.app;

import java.util.ArrayList;
import java.util.Arrays;

import com.nhatdang.config.FileConfig;
import com.nhatdang.config.IConfig;
import com.nhatdang.controller.IController;
import com.nhatdang.controller.MainController;

//The application
public class Application{

	
	//The index of the main controller for running app
	private static final int MAIN_CONTROLLER_INDEX = 0;
	
	//Properties of an application
	private ArrayList<IConfig> configurations;
	private ArrayList<IController> controllers;
	
	public Application() {
		
		//initialize configurations
		configurations = new ArrayList<>(
				Arrays.asList(
						FileConfig.INSTANCE));
		
		//initialize controllers
		controllers = new ArrayList<>(
				Arrays.asList(
						MainController.INSTANCE));
	}
	
	//Run the application
	//Return 0 when there are no error
	public int run() {
		
		//Run the main controllers;
		int errorCode = controllers.get(MAIN_CONTROLLER_INDEX).run();
		
		return errorCode;
	}
}
