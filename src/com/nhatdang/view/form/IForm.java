package com.nhatdang.view.form;

import com.nhatdang.validator.DataValidator;
import com.nhatdang.view.IView;

public interface IForm extends IView {
	
	//Getter for the back flag: to check if the back is pressed
	public boolean getBackFlag();
	
	//Getter for the model: the data from the form
	public Object getModel();
	
	//Setter for the back flag
	public void setBackFlag(boolean value);
	
	//Setter for the model
	public void setModel(Object value);
	
	//Instruction for turning back
	default void printTips() {
		System.out.println("----------------------------------------");
		System.out.println("Tips: To go back to the main menu, please input: " + DataValidator.BACK_SYMBOL);
		System.out.println("----------------------------------------\n");
	}	

	
	//Save the model from the form, 
	//	clear the model, and then return the saved model
	//Return the model from the form
	//If the back is pressed, return null
	public Object fillForm();
	
	//Clear data before turning back
	public default void executeBeforeBack() {
		
		//Clear back flag before turning back
		setBackFlag(false);
	}
	
	//Return the data from the form
	//	After returning data, clear the current data
	public default Object submitModel() {
		
		//Save the current model
		Object data = getModel();
		
		//Clear the model after submitting
		setModel(null);
		
		//return the save model
		return data;
	}
	
	@Override
	public default int showExecute() {
		
		//print the tips in the header
		printTips();
		
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
		int errorCode = (null == data?1:0);
		return errorCode;
	}
}
