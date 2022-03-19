package com.nhatdang.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.service.ISlangWordService;
import com.nhatdang.service.SlangWordService;
import com.nhatdang.view.ExitView;
import com.nhatdang.view.FindView;
import com.nhatdang.view.HistoryView;
import com.nhatdang.view.IView;
import com.nhatdang.view.MenuView;
import com.nhatdang.view.RandomView;

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
						new MenuView(),
						new FindView(FindType.FIND_BY_WORD),
						new FindView(FindType.FIND_BY_KEYWORD),
						new HistoryView(),
						new ExitView()));
		
		//Setup the index
		MAIN_MENU_INDEX = 0;
		EXIT_INDEX = views.size() - 1;
		
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
	
	//Execute for find slang word feature
	//	Return the index of main menu after execute
	private int executeFindView(FindType findType) {
		
		//Load the find view
		FindView findView = (FindView) views.get(currentViewId);
		
		
		//Loop until errorCode == IView.BACK_CODE
		while(true) {
			
			//Show the form to input the word/keyword
			int errorCode = findView.getFindForm().show();
			
			//Break out the loop condition
			if (IView.BACK_CODE == errorCode) {
				break;
			}
			
			//Get the key from the input
			String key = (String)findView.getFindForm().getModel();
			
			//The result slang word list
			List<SlangWord> result = null;
			
			//Using service to get the slang word list with the given key
			if (null != key) {
				if(findType.equals(FindType.FIND_BY_WORD)) {
					result = slangWordService.findByWord(key);
				} else if (findType.equals(FindType.FIND_BY_KEYWORD)) {
					result = slangWordService.findByKeyword(key);
				}
			}
			
			//Set the found slang word and show them into screen
			findView.setAnswers(result).show();
		}
		
		
		//After using find feature (mean using $back feature to come out),
		//	return to main menu
		return MAIN_MENU_INDEX;
	}
	
	//Execute to show finding history
	//	Return the index of main menu after execute
	private int executeHistoryView() {
		
		//Load the history view
		HistoryView view = (HistoryView) views.get(currentViewId);
		
		//Load the history data
		List<SlangWord> data = slangWordService.showFindHistory();
		
		//Set the data and show the view
		view.setHistoryData(data).show();
		
		//After using find feature, return to main menu
		return MAIN_MENU_INDEX;
	}
	
	//Execute to make a random slang word
	//	Return the index of main menu after execute
	private int executeRandomView() {
			
		//Load the history view
		RandomView view = (RandomView) views.get(currentViewId);
			
		//Load the random slang word
		SlangWord randomSlangWord = slangWordService.randomSlangWord();
			
		//Set the random slang word and show the view
		view.setRandomSlangWord(randomSlangWord).show();
			
		//After using find feature, return to main menu
		return MAIN_MENU_INDEX;
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
			else if (1 == currentViewId) {currentViewId = executeFindView(FindType.FIND_BY_WORD);}
			else if (2 == currentViewId) {currentViewId = executeFindView(FindType.FIND_BY_KEYWORD);}
			else if (3 == currentViewId) {currentViewId = executeHistoryView();}
			else if (8 == currentViewId) {currentViewId = executeRandomView();}
			else if (EXIT_INDEX == currentViewId) {currentViewId = executeExitView(); break;}	//break if exit option is choosen
			
		}
		
		return 0;
	}

}
