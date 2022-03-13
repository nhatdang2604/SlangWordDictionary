package com.nhatdang.factory;

import java.util.HashMap;

//Using enum for applying Singleton pattern more safely
public enum SlangWordFactory implements IFactory {
	
	//The instance
	INSTANCE;
	
	private static final String defaultWorkspace = "data.txt";
	
	//Cache to store current slang word
	private HashMap<String, String> cache;
	
	//The current file to read the slang dictionary
	private String currentWorkspace;
	
	//Init attribute for factory
	private void loadCache() {
		//TODO:
	}
	
	//Constructor for enum
	private SlangWordFactory() {
		cache = new HashMap<>();
		currentWorkspace = defaultWorkspace;
		loadCache();
	}
	
	
	
	
}
