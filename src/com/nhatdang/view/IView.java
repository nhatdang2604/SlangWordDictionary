package com.nhatdang.view;

public interface IView {

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
		
		return 0;
	}
	
	//API for showing a view
	default public int show() {
		
		//Clear screen first
		clearScreen();
		
		//Run the implementation
		return showExecute();
	}

	
}
