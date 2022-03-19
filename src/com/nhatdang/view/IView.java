package com.nhatdang.view;

public interface IView {

	//Code for returning in show() and showExecute()
	public static final int NO_ERROR_CODE = 0;
	public static final int BACK_CODE = 1;
	public static final int FAIL_CODE = 2;
	
	//Clear the console screen
	default void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}
			else {
		         Runtime.getRuntime().exec("clear");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		    
	}
	
	//Implementation for showing view
	//	Return 0 when only show view
	//	Return 1 when back to the previous view
	default public int showExecute() {
		//do nothing
		
		return NO_ERROR_CODE;
	}
	
	//API for showing a view
	default public int show() {
		
		//Clear screen first
		clearScreen();
		
		//Run the implementation
		return showExecute();
	}

	
}
