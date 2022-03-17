package com.nhatdang.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nhatdang.service.ISlangWordService;
import com.nhatdang.service.SlangWordService;
import com.nhatdang.view.ExitView;
import com.nhatdang.view.IView;
import com.nhatdang.view.MenuView;

//Using enum for singleton pattern
public enum MainController implements IController {
	
	//instance of the singleton pattern
	INSTANCE;
	
	//All main feature views
	private List<IView> views;
		
	//Id of the current view
	private Integer currentViewId;
	
	//Constant index
	private final int MAIN_MENU_INDEX;
	private final int EXIT_INDEX;

	//Services
	private ISlangWordService slangWordService;
	
	private MainController() {
		views = new ArrayList<>(
				Arrays.asList(
						MenuView.INSTANCE,
						ExitView.INSTANCE));
		
		//Setup the index
		MAIN_MENU_INDEX = 0;
		EXIT_INDEX = views.size();
		
		//Inject the properties
		slangWordService = SlangWordService.INSTANCE;
	}
	
	//Execute for main menu to option choosing
	//	Return the index of selected option
	private int executeMenuView() {
		
		//Load the menu
		MenuView menu = (MenuView) views.get(currentViewId);
		
		//Show the menu
		menu.show();
		
		//Return selected option from main menu
		return menu.getSelectedOption();
	}
	
	//Execute before closing the app
	//Return dummy value
	private int executeExitView() {
		
		//Save the data from the cache to file before exitting
		slangWordService.commitDataToCurrentFile();
		
		//Load the exit view
		ExitView exitView = (ExitView) views.get(currentViewId);
		
		//Clear console screen
		exitView.show();
		
		//Return dummy value
		return EXIT_INDEX;
	}
	
	@Override
	public int run() {
		
		//Always start with the main menu view:
		currentViewId = MAIN_MENU_INDEX;
		
		//The application run until the exit option is choosen
		while (true) {
			
			if (MAIN_MENU_INDEX == currentViewId) {currentViewId = executeMenuView();}
			else if (EXIT_INDEX == currentViewId) {currentViewId = executeExitView(); break;}	//break if exit option is choosen
			
		}
		
		return 0;
	}

}
