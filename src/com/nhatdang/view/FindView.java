package com.nhatdang.view;

import java.util.List;
import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.IForm;

public class FindView implements IView {

	//Find type of the view
	private FindType findType;
	
	//List of found slang words
	private List<SlangWord> foundSlangWords;
	
	//Form for input word/keyword
	private IForm findForm;
	
	public FindView(FindType findType) {
		this.findType = findType;
		foundSlangWords = null;
		findForm = new FindForm(this.findType);
	}
	
	//Set the answers after finding
	public IView setAnswers(List<SlangWord> answers) {
		foundSlangWords = answers;
		return this;
	}
	
	//Getter for form
	public IForm getFindForm() {
		return findForm;
	}
	
	@Override
	public int showExecute() {
			
		//Scanner for input
		Scanner scanner = new Scanner(System.in);
		
		//Check if there are no matching slang word
		if (null == foundSlangWords || foundSlangWords.isEmpty()) {
			System.out.println("Not found any slang word that match");
		} else {
			
			//Print all the found slang word
			System.out.println("Found " + foundSlangWords.size() + " that match: ");
			for (SlangWord slang: foundSlangWords) {
				System.out.println(slang);
			}
			
		}
		
		//Print the next instruction
		System.out.print("\nPress enter to continue: ");
		scanner.nextLine();
		
		//Just need to hit enter, to return to input word/keyword form
		//	=> never get error
		return NO_ERROR_CODE;
	}
}
