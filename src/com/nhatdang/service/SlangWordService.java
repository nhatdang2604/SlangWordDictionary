package com.nhatdang.service;

import java.util.List;

import com.nhatdang.dao.ISlangWordDAO;
import com.nhatdang.dao.SlangWordDAO;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.entity.quiz.QuizGivenDefinition;
import com.nhatdang.entity.quiz.QuizGivenWord;

//Service implement by using enum for singleton pattern
public enum SlangWordService implements ISlangWordService {
	
	//instance for singleton pattern
	INSTANCE;
	
	//slang word data access object
	private ISlangWordDAO slangWordDAO;
	
	//The name give it all
	private static final int NUMBER_OF_OPTIONS_IN_QUIZ = 4;
	
	//Inject properties
	private SlangWordService() {
		slangWordDAO = SlangWordDAO.INSTANCE;
	}
	
	//Find slang word by the given word
	//	Return null if there are no search hit
	@Override
	public List<SlangWord> findByWord(String word) {
		
		//Get the result list
		List<SlangWord> result = 
				slangWordDAO.findSlangWord(word, ISlangWordDAO.FindType.FIND_BY_WORD);
		
		//Return null if there are no result, else return the result
		return (result.isEmpty()?null:result);
	}
	
	//Find slang word which its defination has the keyword
	//	Return null list if there are no search hit
	@Override
	public List<SlangWord> findByKeyword(String keyword){
		
		//Get the result list
		List<SlangWord> result = 
					slangWordDAO.findSlangWord(keyword, ISlangWordDAO.FindType.FIND_BY_KEYWORD);
		
		//Return null if there are no result, else return the result
		return (result.isEmpty()?null:result);
	}
	
	//Show history of found slang words
	@Override
	public List<SlangWord> showFindHistory(){
		return slangWordDAO.showHistory();
	}
	
	//Add new slang word
	//	Return 0 if add successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word already define
	@Override
	public int addSlangWord(SlangWord newSlangWord){
		return slangWordDAO.addSlangWord(newSlangWord);
	}
	
	//Edit an existed slang word
	//	Return 0 if eidt successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word isn't defined, else return 0
	@Override
	public int editSlangWord(SlangWord slangWord){
		return slangWordDAO.updateSlangWord(slangWord);
	}
	
	//Delete an existed slang word by the given word
	//	Return 0 if the delete successfully
	//	Return 1 if the word is null
	//	Return 2 if the slang word with the given word isn't defined
	@Override
	public int deleteSlangWord(String word){
		return slangWordDAO.deleteSlangWord(word);
	}
	
	//Reset the data from dictionary to default
	//	Return 0 if success
	@Override
	public int resetToDefaultData(){
		return slangWordDAO.resetToDefaultSlangWords();
	}
	
	//Randomize an slang word
	//	Return the random slang word
	public SlangWord randomSlangWord() {
		return slangWordDAO.randomSlangWord();
	}
	
	//Make a quiz:
	//	1.) The quiz give a word
	//	2.) Player will be given 4 definitions to choose
	//The function output is the quiz with word object
	@Override
	public QuizGivenWord quizWithWord(){
		
		//Get the number of answer
		final int NUMBER_OF_OPTIONS = NUMBER_OF_OPTIONS_IN_QUIZ;
		
		return new QuizGivenWord(slangWordDAO.randomSlangWordsToMakeQuiz(NUMBER_OF_OPTIONS));
	}

	//Make a quiz:
	//	1.) The quiz give a definition
	//	2.) Player will be given 4 slang words to choose
	//The function output is the quiz with definition object
	@Override
	public QuizGivenDefinition quizWithDefinition(){
		
		//Get the number of answer
		final int NUMBER_OF_OPTIONS = NUMBER_OF_OPTIONS_IN_QUIZ;
				
		return new QuizGivenDefinition(slangWordDAO.randomSlangWordsToMakeQuiz(NUMBER_OF_OPTIONS));
	}

	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	@Override
	public int commitDataToCurrentFile() {
		return slangWordDAO.commitDataToCurrentFile();
	}
	
	//Check if the current cache has the given word from the slang word
	public boolean isContainWord(String word) {
		return slangWordDAO.isContainWord(word);
	}
}
