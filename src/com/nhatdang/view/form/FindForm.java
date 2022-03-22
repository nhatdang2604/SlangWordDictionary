package com.nhatdang.view.form;

import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.validator.DataValidator;
import com.nhatdang.validator.IValidator;

public class FindForm implements IForm {

	//Type of finding method
	private FindType findType;
	
	//Flag to check, if the $back is press
	private boolean backFlag;
	
	//Model of the form: word/keyword
	private String model;
	
	//Validator for input
	private IValidator validator;
	
	public FindForm(FindType findType) {
		this.findType = findType;
		backFlag = false;
		validator = new DataValidator();
	}

	//Return body's text of the form
	private String getPlainBody() {
		
		//Find message
		final String message = 
				findType.equals(FindType.FIND_BY_WORD)?
						"Find slang word by word: ":"Find slang word by keyword: ";
		
		return message;
	}
	
	//Getter for the back flag
	@Override
	public boolean getBackFlag() {return backFlag;}

	//Setter for the back flag
	@Override
	public void setBackFlag(boolean value) {backFlag = value;}
	
	//Getter for the model
	@Override
	public String getModel() {
		return model;
	}
	
	//Setter for model
	@Override
	public void setModel(Object value) {
		model = (String) value;
	}

	
	//Fill the form
	//	return the word/keyword, which is parsed data from the form
	@Override
	public String fillForm() {
		
		//Print the body of the form
		System.out.print(getPlainBody());
		
		//Get data from user's input
		Scanner scanner = new Scanner(System.in);
		String buffer = scanner.nextLine().trim();
		
		//If the buffer is back symbol => set the back flag
		setBackFlag(((DataValidator)validator).isBackPressed(buffer));
		
		//Keep getting the word/keyword from user
		model = buffer;
		
		//Return the model
		return model;
	}

	
	
	

	

}
