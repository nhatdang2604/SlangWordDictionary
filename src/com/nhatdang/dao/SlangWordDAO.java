package com.nhatdang.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nhatdang.config.FileConfig;
import com.nhatdang.config.IConfig;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.utils.Parser;

//Data access object for all the slang word method
public class SlangWordDAO implements ISlangWordDAO {
	
	//Configurations
	private IConfig fileConfig;
	
	//Cache for the current data
	private Map<String, SlangWord> cache;
	
	//Cache for the default data
	private Map<String, SlangWord> historyCache;
	
	//Using singleton pattern
	private static SlangWordDAO instance = new SlangWordDAO();
	public static SlangWordDAO getInstance() {return instance; }
	private SlangWordDAO() {
		
		//Construct properties
		cache = new HashMap<>();
		historyCache = new HashMap<>();
		fileConfig = FileConfig.getInstance();
		
		//Initialize properties
		initComponents();
	}
	
	
	//Initialize the components for startup
	// Return 0 if success
	private int initComponents() {
		
		cache = getAllSlangWords();
		//historyCache = getDefaultSlangWords();
		
		return 0;
	}
	
	//Get the default slang word
	//	Only for construction
	@Override
	public Map<String, SlangWord> getDefaultSlangWords() {
		return null;
	}
	
	//Get all slang words
	@Override
	public Map<String, SlangWord> getAllSlangWords() {
		
		//Return the current cache if
		//	cache != null && cache is not empty
		if (!(null == cache || cache.isEmpty())) return cache;
		
		//Parser to read data from file
		Parser parser = new Parser();
		
		//Read the data from the workspace data file
		cache = parser.readSlangWordFromText(((FileConfig)fileConfig).getWorkspaceDataFile());
		
		//Return the new update cache
		return cache;
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
			String word = target;
			result.add(cache.get(word));
		} 
		else if (type.equals(FindType.FIND_BY_KEYWORD)) {
			
			//Find the slang word by the keyword
			String keyword = target;
			cache.forEach((word, slangWord) -> {
				if (slangWord.getDefinition().contains(keyword)) {
					result.add(slangWord);
				}
			});
			
		}
		
		return result;
	}
		
	//Show the history of finding slang word
	@Override
	public List<SlangWord> showHistory(){
		return null;
	}
		
	//Create new slang word
	// Return 0 if success
	@Override
	public int addSlangWord(SlangWord newSlangWord) {
		return 0;
	}
		
	//Update existed slang word
	// Return 0 if success
	@Override
	public int updateSlangWord(SlangWord slangWord) {
		return 0;
	}
		
	//Delete existed slang word with the given word
	// Return 0 if success
	@Override
	public int deleteSlangWord(String word) {
		return 0;
	}
		
	//Randomize 'size' slang words
	@Override
	public List<SlangWord> randomSlangWords(int size) {
		return null;
	}
	
	//Write the current cache into workspace file
	//	Return 0 if success
	//When to use: when exit program
	@Override
	public int commitDataToCurrentFile() {
		return 0;
	}

}
