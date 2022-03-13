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

	//Getter
	public String getWord() {return word;}
	public String getDefinition() {return definition;}
	
	//Setter
	public void setWord(String word) {this.word = word;}
	public void setDefinition(String definition) {this.definition = definition;}

	
}
