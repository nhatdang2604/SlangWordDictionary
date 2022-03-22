package com.nhatdang.entity.quiz;

import java.util.List;

import com.nhatdang.entity.SlangWord;

public class StringQuiz extends Quiz{

	//List of the options to choose
	protected List<String> options;
			
	//The given word to ask
	protected String given;
	
	public StringQuiz() {
		//do nothing
	}
	
	//Getters
	public List<String> getOptionsString() {return options;}
	public String getGivenString() {return given;}
	
}
