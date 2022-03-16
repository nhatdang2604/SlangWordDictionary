package com.nhatdang.service;

import java.util.List;
import java.util.stream.Collectors;

import com.nhatdang.dao.ISlangWordDAO;
import com.nhatdang.dao.SlangWordDAO;
import com.nhatdang.entity.SlangWord;

//Service implement by using enum for singleton pattern
public enum SlangWordService implements ISlangWordService {
	
	//instance for singleton pattern
	INSTANCE;
	
	//slang word data access object
	private ISlangWordDAO slangWordDAO;
	
	//The name give it all
	private static final int NUMBER_OF_ANSWERS_IN_QUIZ = 4;
	
	//Inject properties
	private SlangWordService() {
		slangWordDAO = SlangWordDAO.INSTANCE;
	}
	
	//Find slang word by the given word
	//	Return null if there are no search hit
	@Override
	public SlangWord findByWord(String word) {
		
		//Get the result list
		List<SlangWord> result = 
				slangWordDAO.findSlangWord(word, ISlangWordDAO.FindType.FIND_BY_WORD);
		
		//Return null if there are no result, else return the result
		return (result.isEmpty()?null:result.get(0));
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
	
	//Make a quiz:
	//	1.) The quiz give a word
	//	2.) Player will be given 4 definitions to choose
	//The function have 3 output:
	//	1.) The return result: 4 definitions to answer
	//	2.) resultIndex: index of the correct answer
	//	3.) word: the word that the quiz gave
	@Override
	public List<String> quizWithWord(int resultIndex, String word){
		
		//Get the number of answer
		final int NUMBER_OF_ANSWER = NUMBER_OF_ANSWERS_IN_QUIZ;
		
		//List of 4 random slang words
		List<SlangWord> answers = 
				slangWordDAO.randomSlangWordsToMakeQuiz(NUMBER_OF_ANSWER, resultIndex);
		
		//Get the word for the player
		word = answers.get(resultIndex).getWord();		
		
		//Return the list of definitions of those slang words
		return answers
				.stream()
				.map(slangWord -> slangWord.getDefinition())
				.collect(Collectors.toList());
	}

	//Make a quiz:
	//	1.) The quiz give a definition
	//	2.) Player will be given 4 slang words to choose
	//The function have 3 output:
	//	1.) The return result: 4 slang words to answer
	//	2.) resultIndex: index of the correct answer
	//	3.) definition: the definition that the quiz gave
	@Override
	public List<String> quizWithDefinition(int resultIndex, String definition){
		
		//Number of answer in a quiz
		final int NUMBER_OF_ANSWER = NUMBER_OF_ANSWERS_IN_QUIZ;
				
		//List of 4 random slang words
		List<SlangWord> answers = 
				slangWordDAO.randomSlangWordsToMakeQuiz(NUMBER_OF_ANSWER, resultIndex);
				
		//Get the definition for the player
		definition = answers.get(resultIndex).getDefinition();
		
		//Return the list of words of those slang words
		return answers
				.stream()
				.map(slangWord -> slangWord.getWord())
				.collect(Collectors.toList());
	}

	
}
