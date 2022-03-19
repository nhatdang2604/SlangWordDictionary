package com.nhatdang.view;

import java.util.List;
import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.IForm;

public class RandomView implements IView {


	//The random slang word
	private SlangWord randomSlangWord;
	
	public RandomView() {
		randomSlangWord = null;
	}
	
	//Set the random slang word data
	public IView setRandomSlangWord(SlangWord randomSlangWord) {
		this.randomSlangWord = randomSlangWord;
		return this;
	}
	
	@Override
	public int showExecute() {
			
		//Scanner for input
		Scanner scanner = new Scanner(System.in);
		
		//Check if there are no history data before
		if (null == randomSlangWord) {
			System.out.println("Today have no slang word!");
		} else {
			
			//Print all the history slang word
			System.out.println("Slang word of the day: ");
			System.out.println(randomSlangWord);
			
		}
		
		//Print the next instruction
		System.out.print("\nPress enter to return to main menu: ");
		scanner.nextLine();
		
		//Just need to hit enter, to return to input word/keyword form
		//	=> never get error
		return NO_ERROR_CODE;
	}
}
