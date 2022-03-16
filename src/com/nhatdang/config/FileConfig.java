package com.nhatdang.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//Store properties for load/save slang words
public class FileConfig implements IConfig{
	
	//Properties to config
	private static final String DEFAULT_DATA_FILE = "default.txt";
	private static final String WORKSPACE_DATA_FILE = "data.txt";
	
	//Using Singleton Pattern for config
	private static FileConfig instance = new FileConfig();
	public static FileConfig getInstance() {return instance;}
	private FileConfig() {
		//do nothing
	}
	
	//Get property
	public String getDefaultDataFile() {return DEFAULT_DATA_FILE;}
	public String getWorkspaceDataFile() {return WORKSPACE_DATA_FILE;}

		
}
