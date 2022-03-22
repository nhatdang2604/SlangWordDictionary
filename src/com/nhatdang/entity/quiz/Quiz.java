package com.nhatdang.entity.quiz;

import java.util.ArrayList;
import java.util.List;

import com.nhatdang.entity.SlangWord;

public class Quiz {
	
	//Index of the correct answer
	protected int answerIndex;
	
	//List of the options to choose
	private List<SlangWord> options;
	
	//The given slang word to ask
	private SlangWord given;

	public Quiz() {
		answerIndex = -1;
		options = new ArrayList<>();
		given = new SlangWord();
	}
	
	public Quiz(int answerIndex, List<SlangWord> options, SlangWord given) {
		this.answerIndex = answerIndex;
		this.options = options;
		this.given = given;
	}

	//Getters
	public int getAnswerIndex() {return answerIndex;}
	public List<SlangWord> getOptions() {return options;}
	public SlangWord getGiven() {return given;}
	
	//Setters
	public void setAnswerIndex(int answerIndex) {this.answerIndex = answerIndex;}
	public void setOptions(List<SlangWord> options) {this.options = options;}
	public void setGiven(SlangWord given) {this.given = given;}
	
	
}
