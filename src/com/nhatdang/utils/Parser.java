package com.nhatdang.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.nhatdang.entity.SlangWord;

public class Parser {

	//Delimiter for the (word, definition) in the data file
	public static final String SLANG_WORD_DELIMITER = "`";
	
	//Parse slang word from a line text
	public SlangWord parse(String line) {
		
		//Delimiter from a .txt file is '`'
		final String needle = SLANG_WORD_DELIMITER;
		
		//Tokenizer for parsing
		StringTokenizer tokenizer = new StringTokenizer(line, needle);
		
		//The result slang word
		SlangWord slangWord = new SlangWord();
		
		//Set properties for slang word
		slangWord.setWord(tokenizer.nextToken());
		slangWord.setDefinition(tokenizer.nextToken());
		
		//Return the given slang word
		return slangWord;
	}
	
	//Read a list of slang words from a .txt file
	public Map<String, SlangWord> readSlangWordFromText(String path) {
		
		//The dictionary of slang words
		Map<String, SlangWord> dictionary = new HashMap<>();
			
		//Return the empty dictionary if the file isn't existed
		File file = new File(path);
		try {
			if (file.createNewFile()) {
				return dictionary;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Try to read the file
		BufferedReader reader = null;
		try {
			
			//Try to read the given data file
			reader = new BufferedReader(new FileReader(file));
			
			//Text line buffer
			String line;
			
			//Parse the slang word into dictionary until the end of file
			while (null != (line = reader.readLine()))  {
				SlangWord buffer = parse(line);
				dictionary.put(buffer.getWord(), buffer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				
			//Try to close the reader
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return dictionary;
	}
	
}
