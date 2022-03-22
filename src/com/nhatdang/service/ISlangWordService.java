package com.nhatdang.service;

import java.util.List;

import com.nhatdang.entity.SlangWord;
import com.nhatdang.entity.quiz.QuizGivenDefinition;
import com.nhatdang.entity.quiz.QuizGivenWord;

public interface ISlangWordService {
	
	//Find slang word by the given word
	public List<SlangWord> findByWord(String word);
	
	//Find slang word which its defination has the keyword
	public List<SlangWord> findByKeyword(String keyword);
	
	//Show history of found slang words
	public List<SlangWord> showFindHistory();
	
	//Add new slang word
	//	Return 0 if add successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word already define
	public int addSlangWord(SlangWord newSlangWord);
	
	//Edit an existed slang word
	//	Return 0 if eidt successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word isn't defined, else return 0
	public int editSlangWord(SlangWord slangWord);
	
	//Delete an existed slang word by the given word
	//	Return 0 if the delete successfully
	//	Return 1 if the word is null
	//	Return 2 if the slang word with the given word isn't defined
	public int deleteSlangWord(String word);
	
	//Reset the data from dictionary to default
	//	Return 0 if success
	public int resetToDefaultData();
	
	//Randomize an slang word
	//	Return the random slang word
	public SlangWord randomSlangWord();
	
	//Make a quiz:
	//	1.) The quiz give a word
	//	2.) Player will be given 4 definitions to choose
	//The function output is the quiz with word object
	public QuizGivenWord quizWithWord();

	//Make a quiz:
	//	1.) The quiz give a definition
	//	2.) Player will be given 4 slang words to choose
	//The function output is the quiz with definition object
	public QuizGivenDefinition quizWithDefinition();

	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	public int commitDataToCurrentFile();
	
	//Check if the current cache has the given word from the slang word
	public boolean isContainWord(String word);
}
