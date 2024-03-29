package com.nhatdang.view.form;

import java.util.Scanner;

import com.nhatdang.validator.ConfirmValidator;
import com.nhatdang.view.IView;
import com.nhatdang.view.form.SlangWordForm.FormMode;

public class ConfirmForm implements IForm {

	//For validating
	private ConfirmValidator validator;
	
	//Hold the confirmation result
	private Boolean isConfirmed;
	
	//Hold the mode of the form
	private FormMode mode;
	
	public ConfirmForm() {
		validator = new ConfirmValidator();
		mode = null;
		isConfirmed = null;
	}
	
	public ConfirmForm(FormMode mode) {
		this.mode = mode;
		validator = new ConfirmValidator();
		isConfirmed = null;
	}
	
	//Fill the form
	//	return confirmation state, which is parsed data from the form
	@Override
	public Boolean fillForm() {
		
		//For input
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			//Clear screen first
			clearScreen();
			
			System.out.print("Are you sure" 
					+ (mode.equals(FormMode.DELETE)?" to delete":"")
					+ "? [Y/N]: ");
			
			String buffer = scanner.nextLine().trim();
			
			//Validate if the input is qualified
			if (validator.isValidInput(buffer)) {
				isConfirmed = (buffer.startsWith("Y") || buffer.startsWith("y"));
				break;
			}
			
		}
		
		return isConfirmed;
	}

	//Override this method to exclude the printTips() 
	@Override
	public int showExecute() {
		
		//print the tips in the header
		//printTips();		//unused
		
		//Fill the form and get the data
		Object data = fillForm();
		
		//If the back flag is got
		//	=> post processing and set data to null
		if(getBackFlag()) {
			executeBeforeBack();
			data = null;
		}
		
		//	Error code == 0: no error
		//	Error code == 1: back is pressed
		int errorCode = (null == data?IView.BACK_CODE:IView.NO_ERROR_CODE);
		return errorCode;
	}
	
	//Dummy method
	@Override
	public boolean getBackFlag() {
		
		//Always return false for dummy getBackFlag() method
		return false;
	}

	@Override
	public Object getModel() {
		return isConfirmed;
	}

	
	//Dummy method
	@Override
	public void setBackFlag(boolean value) {
		//do nothing
	}

	@Override
	public void setModel(Object value) {
		isConfirmed = (Boolean)value;
	}

}
