package com.nhatdang.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nhatdang.view.WarningView;

public class ConfirmValidator implements IValidator {

	//View for popup error
	private WarningView warningView;
	
	//List of an accepted input
	private static final List<String> ACCEPTED_INPUT = new ArrayList<>(
			Arrays.asList(
					"y", "n", 
					"Y", "N", 
					"yes", "no", 
					"Yes", "No", 
					"YES", "NO"));
	
	//List of errors
	private static final List<String> ERRORS = new ArrayList<>(
			Arrays.asList(
					"The input must be in those cases:\n" 
					+ "1.) y/n\n"
					+ "2.) Y/N\n"
					+ "3.) yes/no\n"
					+ "4.) Yes/No\n"
					+ "5.) YES/NO\n"));
	
	
	
	public ConfirmValidator() {
		warningView = new WarningView(null);
	}
	
	//Check if the input is y/n or Y/N or yes/no or YES/NO or Yes/No
	public boolean isValidInput(String buffer) {
		
		boolean result = ACCEPTED_INPUT.contains(buffer);
		if (!result) {
			warningView.setErrorMessage(ERRORS.get(0)).show();
		}
		
		return result;
	}
	
}
