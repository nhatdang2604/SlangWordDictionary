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
		
		//To delete a slang word => must find by word
		findForm = new FindForm(FindType.FIND_BY_WORD);
		
		//Validator for checking the existance of the delete slang word
		validator = new SlangWordValidator();
		
		//Confirm form for deleting
		confirmForm = new ConfirmForm(FormMode.DELETE);
	}	
	
	//Getter for find view
	public FindForm getFindForm() {
		return findForm;
	}
	
	
	@Override
	public int showExecute() {
	
		//Scanner for input
		Scanner scanner = new Scanner(System.in);

		while(true) {
		
			//Return to back code if user input $back in the find slang word form
			if (IView.BACK_CODE == findForm.show()) {return BACK_CODE;}
		
			//Try to show the confirmation dialog
			confirmForm.show();
			
			//If the user cancel the confirmation => back to the find form
			if (!(Boolean)confirmForm.getModel()) {continue;}
			
			//Get the input word from user
			String key = findForm.getModel();
		
			//Check if the delete slang word is not existed
			if (!validator.isSlangWordNotExist(key)) {
				
				//Print the successfully reported
				System.out.println("The slang word has been deleted!");
				System.out.print("\nPress enter to return to main menu: ");
				scanner.nextLine();
				
				//Break out the loop, if everything is valid
				break;
			}
			
		} 

		//Return no error code if the delete process is success
		return NO_ERROR_CODE;
	}
}
