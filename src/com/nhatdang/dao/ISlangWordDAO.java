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
	
	//reset the default slang words
	//	Return 0 if success
	public int resetToDefaultSlangWords();
	
	//Find the slang word, by the given target, specify by type..
	//	type == 0: target = word of the slang word
	//	type == 1: target = keyword of the definition tof the slang word
	public List<SlangWord> findSlangWord(String target, FindType type);
	
	//Show the history of finding slang word
	public List<SlangWord> showHistory();
	
	//Create new slang word
	//	Return 0 if add successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word already define
	public int addSlangWord(SlangWord newSlangWord);
	
	//Update existed slang word
	//	Return 0 if update successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word isn't defined, else return 0
	public int updateSlangWord(SlangWord slangWord);
	
	//Delete existed slang word with the given word
	//	Return 0 if the delete successfully
	//	Return 1 if the word is null
	//	Return 2 if the slang word with the given word isn't defined
	public int deleteSlangWord(String word);
	
	//Randomize an slang word
	//	Return the random slang word
	public SlangWord randomSlangWord();
	
	//	Randomize 'size' slang words and make answer to create quiz
	//	The function has 2 output
	//		1.) The return result: list of the 'size' slang words
	//		2.) answerIndex: the correct answer index in the list
	public List<SlangWord> randomSlangWordsToMakeQuiz(int size, int answerIndex);
	
	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	public int commitDataToCurrentFile();
	
	//Check if the current cache has the given word from the slang word
	public boolean isContainWord(String word);
}
