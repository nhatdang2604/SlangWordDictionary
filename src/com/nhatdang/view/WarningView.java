package com.nhatdang.view;

import java.util.Scanner;

public class WarningView implements IView {

	private static final String RETURN_MESSAGE = "Input enter to try again";
	private String errorMessage;
	
	public WarningView(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public WarningView() {
		//do nothing
	}

	//Getter and setter
	//	Setter using builder pattern for reduce coding
	public String getErrorMessage() {return errorMessage;}
	public WarningView setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}


	@Override
	public void showExecute() {
		
		//Popup the message
		System.out.println(errorMessage);
		System.out.println(RETURN_MESSAGE);
		
		//Make dummy scanner to enter 
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
	
}
