package com.nhatdang.config;

//Store properties for load/save slang words
//	Using enum for singleton pattern
public enum FileConfig implements IConfig{
	
	//Instance of the config, using singleton pattern
	INSTANCE;
	
	//Properties to config
	private static final String DEFAULT_DATA_FILE = "default.txt";
	private static final String WORKSPACE_DATA_FILE = "data.txt";
	
	private FileConfig() {
		//do nothing
	}
	
	//Get property
	public String getDefaultDataFile() {return DEFAULT_DATA_FILE;}
	public String getWorkspaceDataFile() {return WORKSPACE_DATA_FILE;}

		
}
