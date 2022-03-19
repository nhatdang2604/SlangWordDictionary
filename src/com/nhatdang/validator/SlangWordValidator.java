package com.nhatdang.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nhatdang.entity.SlangWord;
import com.nhatdang.service.ISlangWordService;
import com.nhatdang.service.SlangWordService;
import com.nhatdang.view.WarningView;

public class SlangWordValidator implements IValidator {
	
	//View for popup error
	private WarningView warningView;
	
	//Service for validating
	private ISlangWordService slangWordService;
	
	//Model for validating
	private SlangWord model;
	
	private static final List<String> INVALID_WORDS = new ArrayList<>(
			Arrays.asList(
					"`"));
	
	private static final List<String> ERRORS = new ArrayList<>(
			Arrays.asList(
					"Slang word is already existed",
					"Field has invalid character: " + INVALID_WORDS));
	
	
	public SlangWordValidator() {
		warningView = new WarningView(null);
		slangWordService = SlangWordService.INSTANCE;
	}
	
	//Setter for model
	public void setModel(SlangWord slangWord) {this.model = slangWord; }
	
	
	//Try to find the slang word with the given word
	public boolean isStudentExist(int id) {
		
		boolean result = (null != slangWordService.findByWord(model.getWord()));
		if (!result) {
			warningView.setErrorMessage(ERRORS.get(0)).show();
		}
		
		return result;
	}
	
	//Check if the given buffer from user is contain invalid word
	public boolean isContainInvalidWord(String buffer) {
		
		for (String word: INVALID_WORDS) {
			if (buffer.contains(word)) {
				warningView.setErrorMessage(ERRORS.get(1)).show();
				return true;
			}
		}
		
		return false;
	}
}
