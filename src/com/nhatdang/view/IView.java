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
	default public void showExecute() {
		//do nothing
	}
	
	//API for showing a view
	default public void show() {
		
		//Clear screen first
		clearScreen();
		
		//Run the implementation
		showExecute();
	}

	
}
