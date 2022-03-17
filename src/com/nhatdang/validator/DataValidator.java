package com.nhatdang.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nhatdang.view.WarningView;

public class DataValidator implements IValidator {
	

	//The back symbol: for input to back to previous view
	public static final String BACK_SYMBOL = "$back";	
	
	//View for popup error
	private WarningView warningView;
	
	//List of errors
	private static final List<String> ERRORS = new ArrayList<>(
			Arrays.asList(
					"The selected option is out of range",
					"The input must be integer"));
	
	
	public DataValidator() {
		warningView = new WarningView();
	}
	
	//Validate integer format
	public boolean isInteger(String buffer) {
		
		try {
			int value = Integer.parseInt(buffer);
		} catch (NumberFormatException e) {
			warningView.setErrorMessage(ERRORS.get(1)).show();
			return false;
		}
		
		return true;
	}
	
	//Validate if the given buffer is out of range
	public boolean isIntegerOutOfRange(String buffer, int min, int max) {
		
		//Get the integer value from the buffer
		int value = Integer.parseInt(buffer);
		
		//Check if the value is out of range
		if (!((min <= value) && (value <= max))) {
			warningView.setErrorMessage(ERRORS.get(0)).show();
			return false;
		}
		
		return true;
	}
	
	//Validate if the buffer is BACK_SYMBOL
	public boolean isBackPressed(String buffer) {
		return buffer.equals(BACK_SYMBOL);
	}
	
}
