package com.nhatdang.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.nhatdang.entity.SlangWord;

public class DataWriter {

	//Write a dictionary of slang word into a file which has the given path
	public int writeSlangWordsToFile(Map<String, SlangWord> dictionary, String path) { 
	
		//Error code
		int errorCode = 0;
		
		//Try to open the data file
		BufferedWriter writer = null;
		try {
			
			
			//Try to open and ready to write into the file with the given path
			writer = new BufferedWriter(new FileWriter(path));
						
			//Write all the slang words in the dictionary into the file
			Set<Entry<String, SlangWord>> set = dictionary.entrySet();
			for (Map.Entry<String, SlangWord> entry: set) {
				
				//Data to write
				SlangWord slangWord = entry.getValue();
				
				//Write data to file, line by line
				writer.write(slangWord.getWord() 
						+ Parser.SLANG_WORD_DELIMITER 
						+ slangWord.getDefinition() + "\r\n");
			}
			
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			errorCode = 1;
		} finally {
			
			//Try to close the writer
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return errorCode;
	}
}
