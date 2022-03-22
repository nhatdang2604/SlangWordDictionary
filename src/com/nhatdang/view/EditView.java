package com.nhatdang.view;

import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.validator.SlangWordValidator;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.SlangWordForm;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class EditView implements IView {

	//Form for finding the edit slang word
	private FindForm findForm;
	
	//Form for submit the editted data
	private SlangWordForm dataForm;
	
	//Validator for checking if the slang word is existed
	private SlangWordValidator validator;
	
	public EditView() {
		
		//To edit a slang word => must find by word
		findForm = new FindForm(FindType.FIND_BY_WORD);
		
		//The form the hold the edit data
		dataForm = new SlangWordForm(FormMode.UPDATE);
		
		//Validator for checking the existance of the found slang word
		validator = new SlangWordValidator();
	}	
	
	//Getter for find view
	public FindForm getFindForm() {
		return findForm;
	}
	
	//Getter for slang word form
	public SlangWordForm getDataForm() {
		return dataForm;
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
		
			//Check if the edit slang word is not existed
			if (validator.isSlangWordNotExist(key)) {
				
				//Back to the find slang word form
				continue;
			}
		
			//Inject a dummy slang word with the given key, to the data model
			dataForm.setModel(new SlangWord(key, null));
			
			//Return to the find form if user input $back in the update data form
			int dataFormErrorCode = dataForm.show();
			if (IView.BACK_CODE == dataFormErrorCode) {
				continue;
			} 
			else if (IView.NO_ERROR_CODE == dataFormErrorCode) {
				
				//Print the successfully reported
				System.out.println("The slang word has been editted!");
				System.out.print("\nPress enter to return to main menu: ");
				scanner.nextLine();
				
				//Break out the loop, if everything is valid
				break;
			}
		
		} 

		//Retrun no error code if the update process is success
		return NO_ERROR_CODE;
	}
}
