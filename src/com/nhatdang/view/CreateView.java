package com.nhatdang.view;

import java.util.Scanner;

import com.nhatdang.view.form.IForm;
import com.nhatdang.view.form.SlangWordForm;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class CreateView implements IView {

	//The form to fill the slangword information
	private SlangWordForm form;
	
	//Check if the submit slang word is existed 
	private boolean isExisted;
	

	public CreateView() {
		form = new SlangWordForm(FormMode.CREATE);
		isExisted = false;
	}
	
	//Setter for isExisted
	public IView setState(boolean isExisted) {
		this.isExisted = isExisted;
		return this;
	}
	
	//Getter for form
	public IForm getSlangWordForm() {
		return form;
	}
	
	
	@Override
	public int showExecute() {
		
		//Scanner for input
		Scanner scanner = new Scanner(System.in);
		
		//Default status is creating successfully
		String message = "The new slang word has been created!";
		String instruction = "Press enter to return to main menu!";
		int errorCode = NO_ERROR_CODE;
		
		//Change the message base on the exitance of the model
		if(isExisted) {
			message = "The slang word is already existed!";
			instruction = "Press enter to return to the form!";
			errorCode = FAIL_CODE;
		}
		
		//Print the message and instruction
		System.out.println(message);
		System.out.print(instruction);
		
		//For blocking user input
		scanner.nextLine();	
		
		//Return the error code
		return errorCode;
	}
}
