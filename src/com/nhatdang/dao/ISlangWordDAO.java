package com.nhatdang.dao;

import java.util.List;
import java.util.Map;

import com.nhatdang.entity.SlangWord;

public interface ISlangWordDAO {

	//Find type of finding slang word
	public static enum FindType {
		FIND_BY_WORD, FIND_BY_KEYWORD;
	}
	
	//Get all slang words
	public Map<String, SlangWord> getAllSlangWords();
	
	//Get the default slang words
	public Map<String, SlangWord> getDefaultSlangWords();
	
	//Find the slang word, by the given target, specify by type..
	//	type == 0: target = word of the slang word
	//	type == 1: target = keyword of the definition tof the slang word
	public List<SlangWord> findSlangWord(String target, FindType type);
	
	//Show the history of finding slang word
	public List<SlangWord> showHistory();
	
	//Create new slang word
	// Return 0 if success
	public int addSlangWord(SlangWord newSlangWord);
	
	//Update existed slang word
	// Return 0 if success
	public int updateSlangWord(SlangWord slangWord);
	
	//Delete existed slang word with the given word
	// Return 0 if success
	public int deleteSlangWord(String word);
	
	//Randomize 'size' slang words
	public List<SlangWord> randomSlangWords(int size);
	
	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	public int commitDataToCurrentFile();
	
}
