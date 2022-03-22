package com.nhatdang.view;

import java.util.Scanner;

//Usage: Exit view used for clearing screen
public class ResetToDefaultView implements IView {

	public ResetToDefaultView () {
		//do nothing
	}

	@Override
	public int showExecute() {
		
		//Popup information text
		System.out.println("The data has been reset to default!");
		
		//Print the next instruction
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPress enter to return to main menu: ");
		scanner.nextLine();
		
		return NO_ERROR_CODE;
	}
}
