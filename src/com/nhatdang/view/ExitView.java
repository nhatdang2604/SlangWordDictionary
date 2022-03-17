package com.nhatdang.view;

//Using enum for singleton pattern
//Usage: Exit view used for clearing screen
public enum ExitView implements IView {

	//Instance of the singleton entity
	INSTANCE;
	
	private ExitView () {
		//do nothing
	}

}
