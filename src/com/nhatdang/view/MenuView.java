package com.nhatdang.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.nhatdang.validator.DataValidator;
import com.nhatdang.validator.IValidator;

//Using enum for singleton pattern
public enum MenuView implements IView {

	//Instance for singleton pattern
	INSTANCE;
	
	//Decorator
	private static final String SEPERATOR = "--------------------------";
	private static final String TITLE = 	"-----------MENU-----------";
	
	//List of menu options
	private static final List<String> options = new ArrayList<>(
			Arrays.asList(
					"1. Find slang word by word",
					"2. Find slang word by keyword",
					"3. Show history",
					"4. Add new a slang word",
					"5. Edit a slang word",
					"6. Delete a slang word",
					"7. Reset data to default",
					"8. Random a slang word", 
					"9. Quiz: Given a word",
					"10. Quiz: Given a definition",
					"11. Exit"));
	
	
	//Store the selected option in menu
	private int selectedOption;
	
	//Validator for input from main menu
	private IValidator menuValidator;
	
	//Using Singleton Pattern
	private MenuView() {
		menuValidator = new DataValidator();
	}
	
	//Show the header of menu
	private void showHeaderMenu() {
		
		//Print header
		System.out.println(SEPERATOR + "\n");
		System.out.println(TITLE);
		System.out.println("\n" + SEPERATOR + "\n");
	}
	
	//Show the body of the menu
	private void showBodyMenu() {
		
		//Print all the option into main menu
		options.forEach(option -> {
			System.out.println(option);
		});
	}
	
	//Show the footer of the menu
	private void showFooterMenu() {
		
		//Print footer
		System.out.println("\n" + SEPERATOR + "\n");
		final String inputMessage = "Please choose the index of an option: ";
		System.out.print(inputMessage);

	}
	
	//Show the plain text of menu by:
	//	1.) Show header
	//	2.) Show body
	//	3.) Show footer
	private void showPlainMenu() {
		showHeaderMenu();
		showBodyMenu();
		showFooterMenu();
	}
	
	//Clear screen and show the plain text of menu
	private void reloadPlainMenu() {
		
		//Re-popup all the menu text
		clearScreen();
		showPlainMenu();
	}
	
	//Read the user input from console
	private int readSelectedOption() {
		
		//For reading buffer
		Scanner scanner = new Scanner(System.in);
		
		//The index of selected option
		int option = 0;
		
		//Handle selected out of range
		int optionSize = options.size();
		
		//Casting into data validator
		DataValidator dataValidator = (DataValidator)menuValidator;
		
		//Input until there are no format error:
		while (true) {
			
			//Get the data from user
			String buffer = scanner.nextLine().trim();
			
			//Check if the input is number
			if (dataValidator.isInteger(buffer)) {
				
				//Check if the input is out of range [1; optionSize]
				if (dataValidator.isIntegerOutOfRange(buffer, 1, optionSize)) {
					
					//Save the selected correct format option
					option = Integer.parseInt(buffer);
					
					//Break out the loop if the format is correct
					break;
				}
			}
			
			//Clear screen and reload the menu, 
			//	keep input until there are no input format error
			reloadPlainMenu();
		}
		
		return option;
	}
	
	//Getter
	public List<String> getOptions() {return options;}
	public int getSelectedOption() {return selectedOption;}
	
	@Override
	public int showExecute() {
		
		//show the menu text
		showPlainMenu();
		
		//Read the data from user
		selectedOption = readSelectedOption();
	
		//Main menu doesn't have "back" feature 
		//	=> always return 0;
		return 0;
	}
	
}
