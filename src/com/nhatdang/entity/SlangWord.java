package com.nhatdang.entity;

//Entity of the project
public class SlangWord {
		
	//Attributes
	private String word;
	private String definition;
	
	public SlangWord() {
		//do nothing
	}
	
	public SlangWord(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}

	//Getters
	public String getWord() {return word;}
	public String getDefinition() {return definition;}
	
	//Setters
	public void setWord(String word) {this.word = word;}
	public void setDefinition(String definition) {this.definition = definition;}

	@Override
	public SlangWord clone() {
		return new SlangWord(this.word, this.definition);
	}

	@Override
	public String toString() {
		return "SlangWord [word = " + word + ", definition = " + definition + "]"; 
	}	
	
}
