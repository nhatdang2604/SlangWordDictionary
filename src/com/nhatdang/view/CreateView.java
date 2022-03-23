package com.nhatdang.view;

import java.util.Scanner;

import com.nhatdang.view.form.IForm;
import com.nhatdang.view.form.SlangWordForm;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class CreateView implements IView {

	//The form to fill the slangword information
	private SlangWordForm form;
	
	public CreateView() {
		form = new SlangWordForm(FormMode.CREATE);
	}
	
	//Getter for form
	public IForm getSlangWordForm() {
		return form;
	}
	
	
	@Override
	public int showExecute() {
		
		int errorCode = form.show();
		
		//Success text when submit successfully
		if (IView.NO_ERROR_CODE == errorCode) {
			
			clearScreen();
			
			//Print the message and instruction
			System.out.println("The new slang word has been created!");
			System.out.print("Press enter to return to main menu!");
		
			//For blocking user input
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();	
		
		}
		
		//Return the error code
		return errorCode;
	}
}
