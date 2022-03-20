package com.nhatdang.view;

import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.validator.SlangWordValidator;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.SlangWordForm;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class DeleteView implements IView {

	//Form for finding the delete slang word
	private FindForm findForm;
	
	//Validator for checking if the slang word is existed
	private SlangWordValidator validator;
	
	public DeleteView() {
		
		//To delete a slang word => must find by word
		findForm = new FindForm(FindType.FIND_BY_WORD);
		
		//Validator for checking the existance of the delete slang word
		validator = new SlangWordValidator();
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
