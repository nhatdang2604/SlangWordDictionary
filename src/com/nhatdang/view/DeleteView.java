package com.nhatdang.view;

import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.validator.SlangWordValidator;
import com.nhatdang.view.form.ConfirmForm;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class DeleteView implements IView {

	//Form for finding the delete slang word
	private FindForm findForm;
	
	//Form for delete confirmation
	private ConfirmForm confirmForm;
	
	//Validator for checking if the slang word is existed
	private SlangWordValidator validator;
	
	public DeleteView() {
		
		//Form mode for the delete view
		final FormMode formMode = FormMode.DELETE;
		
		//To delete a slang word => must find by word
		findForm = new FindForm(FindType.FIND_BY_WORD, formMode);
		
		//Validator for checking the existance of the delete slang word
		validator = new SlangWordValidator();
		
		//Confirm form for deleting
		confirmForm = new ConfirmForm(formMode);
	}	
	
	//Getter for find view
	public FindForm getFindForm() {
		return findForm;
	}
	
	
	@Override
	public int showExecute() {
	
		//Scanner for input
		Scanner scanner = new Scanner(System.in);

		//Default value for error code
		int errorCode = NO_ERROR_CODE;
		
		while(true) {
		
			//Return to back code if user input $back in the find slang word form
			if (IView.BACK_CODE == findForm.show()) {
				errorCode = BACK_CODE; 
				break;
			}
		
			//Get the input word from user
			String key = findForm.getModel();
			
			//If the slang word is not exist => continue the loop to input word again
			if(validator.isSlangWordNotExist(key)) {continue;}
			
			//Try to show the confirmation dialog
			confirmForm.show();
			
			//If the user cancel the confirmation => back to the find form
			if (!(Boolean)confirmForm.getModel()) {continue;}
			
			//if the delete slang word is existed
			//	=> Print the successfully reported
			clearScreen();
			System.out.println("The slang word has been deleted!");
			System.out.print("\nPress enter to return to main menu: ");
			scanner.nextLine();
			
			//Break out the loop, if everything is valid
			break;
			
		} 

		//Return the errorCode
		return errorCode;
	}
}
