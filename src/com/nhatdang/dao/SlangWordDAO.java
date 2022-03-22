package com.nhatdang.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.nhatdang.config.FileConfig;
import com.nhatdang.config.IConfig;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.utils.DataWriter;
import com.nhatdang.utils.Parser;

//Data access object for all the slang word method
//Using enum for singleton pattern
public enum SlangWordDAO implements ISlangWordDAO {
	
	//Instance for the singleton pattern
	INSTANCE;
	
	//Configurations
	private IConfig fileConfig;
	
	//Cache for the current data
	private Map<String, SlangWord> cache;
	
	//Cache for the default data
	private List<SlangWord> historyCache;
	
	//Random number generator for creating quiz
	private Random rng;
	
	//Using singleton pattern
	private SlangWordDAO() {
		
		//Construct properties
		cache = new HashMap<>();
		historyCache = new ArrayList<>();
		fileConfig = FileConfig.INSTANCE;
		rng = new Random();
		
		//Initialize properties
		initComponents();
	}
	
	
	//Initialize the components for startup
	// Return 0 if success
	private int initComponents() {
		
		//Load the cache from the data.txt when startup
		cache = getAllSlangWords();
		
		return 0;
	}
	
	//Utilities for code reuseable
	private Map<String, SlangWord> readDataFromFile(String path) {
		
		//Parser to read data from default.txt
		Parser parser = new Parser();
		
		//Read the data from the given file
		cache = parser.readSlangWordFromText(path);
		
		return cache;
	}
	
	//Utilities for add found slang words into history cache
	//	Return 0 when success
	private int addToHistory(SlangWord slangWord) {
		
		//If slang word == null => do nothing and return error code
		if (null == slangWord) {
			return 1;
		}
		
		//add found slang word into cache
		historyCache.add(slangWord);
		
		return 0;
	}
	
	//Get the default slang word
	//	Return 0 if success
	@Override
	public int resetToDefaultSlangWords() {
		
		//Read the data from the default.txt
		readDataFromFile(((FileConfig)fileConfig).getDefaultDataFile());
		
		return 0;
	}
	
	//Get all slang words
	@Override
	public Map<String, SlangWord> getAllSlangWords() {
		
		//Return the current cache if
		//	cache != null && cache is not empty
		if (!(null == cache || cache.isEmpty())) return cache;
		
		//Read the data from the data.txt
		return readDataFromFile(((FileConfig)fileConfig).getWorkspaceDataFile());
	}
		
	//Find the slang word, by the given target, specify by type..
	//	type == FIND_BY_WORD: target = word of the slang word
	//	type == FIND_BY_KEYWORD: target = keyword of the definition tof the slang word
	@Override
	public List<SlangWord> findSlangWord(String target, FindType type){
		
		//The answer after finding
		List<SlangWord> result = new ArrayList<>();
		
		//Find by word execution
		if (type.equals(FindType.FIND_BY_WORD)) {
			
			//Only find the first result
			String word = target.trim();
			SlangWord foundSlangWord = cache.get(word);
			
			//Add to history and add to the result if search hit
			if (null != foundSlangWord) {
				addToHistory(foundSlangWord);
				result.add(foundSlangWord);
			}
		} 
		else if (type.equals(FindType.FIND_BY_KEYWORD)) {
			
			//Find the slang word by the keyword
			String keyword = target.trim();
			cache.forEach((word, slangWord) -> {
				if (slangWord.getDefinition().contains(keyword)) {
					result.add(slangWord);
				}
			});
			
			//Add the found slangwords into cache
			result.forEach(slangWord ->  {
				addToHistory(slangWord);
			});
		}
		
		return result;
	}
		
	//Show the history of finding slang word
	@Override
	public List<SlangWord> showHistory(){
		return historyCache;
	}
		
	//Create new slang word
	//	Return 0 if add successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word already define
	@Override
	public int addSlangWord(SlangWord newSlangWord) {
		
		//Return 0 if add successfully
		//Return 1 if the slang word is null
		//Return 2 if the slang word already define
		int errorCode = (null == newSlangWord?1:
						(cache.containsKey(newSlangWord.getWord())?2:0));

		
		//Add the new slang word into dictionary
		cache.put(newSlangWord.getWord(), newSlangWord);
		
		return errorCode;
	}
		
	//Update existed slang word
	//	Return 0 if update successfully
	//	Return 1 if the slang word is null
	//	Return 2 if the slang word isn't defined, else return 0
	@Override
	public int updateSlangWord(SlangWord slangWord) {
		
		//Return 0 if update successfully
		//Return 1 if the slang word is null
		//Return 2 if the slang word isn't defined, else return 0
		int errorCode = (null == slangWord?1:
						(null == cache.replace(slangWord.getWord(), slangWord)?2:0));
		
		return errorCode;
	}
		
	//Delete existed slang word with the given word
	//	Return 0 if the delete successfully
	//	Return 1 if the word is null
	//	Return 2 if the slang word with the given word isn't defined
	@Override
	public int deleteSlangWord(String word) {
		
		//Return 0 if the delete successfully
		//Return 1 if the word is null
		//Return 2 if the slang word with the given word isn't defined
		int errorCode = (null == word?1:
						(null == cache.remove(word)?2:0));
				
		return errorCode;
	}
	
	//Randomize an slang word
	//	Return the random slang word
	@Override
	public SlangWord randomSlangWord() {
		
		//Buffer for all value in the dictionary
		ArrayList<SlangWord> buffer = (ArrayList<SlangWord>) cache.values();
		
		//Randomize slang word by randomizing the index from [0; buffer.size() - 1] of the buffer
		SlangWord randomSlangWord = buffer.get(rng.nextInt(buffer.size()));
		
		return randomSlangWord;
	}
		
	
	//	Randomize 'size' slang words and make answer to create quiz
	//	The function has 2 output
	//		1.) The return result: list of the 'size' slang words
	//		2.) answerIndex: the correct answer index in the list
	@Override
	public List<SlangWord> randomSlangWordsToMakeQuiz(int size, int resultIndex) {
		
		//Return null if there are not enough slang word in dictionary
		if (cache.size() < size) return null;
		
		//The target 
		ArrayList<SlangWord> result = new ArrayList<>();
	
		//Buffer for all value in the dictionary
		ArrayList<SlangWord> buffer = (ArrayList<SlangWord>) cache.values();
		
		//Start randomize
		int counter = 0;
		do  {
			
			int index = rng.nextInt(size);	//index of the lucky slang word
			SlangWord randomSlangWord = buffer.get(index);	//Get the lucky slang word
			if(!result.contains(randomSlangWord)) {
				++counter;
				result.add(randomSlangWord);//add the lucky slang word into result
			}
				
		} while (size != counter);
		
		//Make the correct answer
		resultIndex = rng.nextInt(size);
		
		return result;
	}
	
	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	@Override
	public int commitDataToCurrentFile() {
		
		//Writer to write into file
		DataWriter writer = new DataWriter();
		
		//Write data from cache into the data.txt
		int errorCode = writer.writeSlangWordsToCSV(cache, 
				((FileConfig)fileConfig).getWorkspaceDataFile());
		
		return errorCode;
	}

	//Check if the current cache has the given word from the slang word
	public boolean isContainWord(String word) {
		return cache.containsKey(word);
	}
	
}
