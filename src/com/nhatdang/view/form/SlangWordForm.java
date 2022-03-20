package com.nhatdang.view.form;

import java.util.Scanner;

import com.nhatdang.entity.SlangWord;
import com.nhatdang.validator.DataValidator;
import com.nhatdang.validator.SlangWordValidator;

public class SlangWordForm implements IForm {
	
	//Mode of the form for CRUD
	public static enum FormMode {
			CREATE, READ, UPDATE, DELETE
	}
	
	//Model of the form: the slang word
	private SlangWord model;
	
	//The mode of the form
	private FormMode mode;
	
	//Validators
	SlangWordValidator slangWordValidator;
	DataValidator dataValidator;
	
	//The back flag
	private boolean backFlag;	
	
	//Output seperator
	private static final String SEPERATOR = ": ";
	
	public SlangWordForm(FormMode mode) {
		this.mode = mode;
		slangWordValidator = new SlangWordValidator();
		dataValidator = new DataValidator();
		backFlag = false;
	}
	
	//Getter for the back flag
	@Override
	public boolean getBackFlag() {return backFlag;}

	//Setter for the back flag
	@Override
	public void setBackFlag(boolean value) {backFlag = value;}
		
	//Getter for the model
	@Override
	public SlangWord getModel() {
		return model;
	}
		
	//Setter for model
	@Override
	public void setModel(Object value) {
		model = (SlangWord) value;
	}
	
	//Format the input buffer from user
	private String modifyStringInput(String buffer) {
		
		//Return " " if input nothing
		if (null == buffer) {return " ";}
		
		//Trim the space
		String result = buffer.trim();
		
		//If the input is all space => return " " 
		if (result.equals("")) {return " ";}
		
		//Check if user input "$back"
		boolean isBackPressed = dataValidator.isBackPressed(result);
		
		//Return null and set the back flag, if $back is pressed
		setBackFlag(isBackPressed);
		if(isBackPressed) {result = null;}
		
		return result;
	}
	
	private void printHeader() {
		System.out.println("----------------------------------------");
		System.out.println("-------------" + mode + " SLANG WORD-------------");
		System.out.println("----------------------------------------\n");
	}
	
	//Repopulate data base on current type field:
	//	level 0: typing word
	//	level 1: typing definition
	private void repopulateData(int level) {
		
		
		//Clear error screen
		clearScreen();
		
		//Reprint the header and the tips
		printTips();
		printHeader();
		
		//Seperator for output
		final String seperator = SEPERATOR;
		
		//Repopulate data base on the given level:
		//	try to repopulate all fields which has less level than the current level
		for (int i = 0; i < level; ++i) {
			if (level > i) {
				System.out.println(SlangWord.FIELD_NAMES.get(i) + seperator + 
						(0 == i? model.getWord():
						(1 == i? model.getDefinition():"")));
			}
		}
	}
	
	//Return:
	//	True if data is valid
	//	False if the input is $back
	private boolean addDataToModel(int level) {
		
		Scanner scanner = new Scanner(System.in);
		final String seperator = SEPERATOR;
		
		while (true) {
			System.out.print(SlangWord.FIELD_NAMES.get(level) + seperator);
			String input = modifyStringInput(scanner.nextLine());		
			
			//Terminate if the input is $back
			if (null == input) return false;
			
			//Check if the input contain forbidden word
			if(!slangWordValidator.isContainInvalidWord(input)) {
				
				if (0 == level) {model.setWord(input); break;}
				else if (1 == level) {model.setDefinition(input); break;}
		
			}
			
			//Clear screen and repopulate data if the input is invalid
			repopulateData(level);
		}
		
		return true;
	}
	
	//Fill the form
	//	return the slang word, which is parsed data from the form
	public SlangWord fillForm() {
		
		//Print the headder
		printHeader();
		
		//Input each field: 
		int size = SlangWord.FIELD_NAMES.size();
		
		//Default situation is mode.equals(FormMode.CREATE)
		int startLevel = 0;
		
		//Check if the mode.equals(FormMode.UPDATE)
		if (mode.equals(FormMode.UPDATE)) {
			
			//Ignore the Word fields of slang word => because this field is uneditable
			startLevel = 1;
			
			//Populate the Word field data
			repopulateData(startLevel);
		}
		else if (mode.equals(FormMode.CREATE)) {
			
			//Recreate the Model for the form
			model = new SlangWord();
		}
		
		for (int level = startLevel; level < size; ++level) {
			
			//If the input is $back => terminate
			if (!addDataToModel(level)) {
				return model;
			}
			
		}
				
		return model;
	}
	
	
}
