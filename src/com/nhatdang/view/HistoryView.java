package com.nhatdang.view;

import java.util.List;
import java.util.Scanner;

import com.nhatdang.dao.ISlangWordDAO.FindType;
import com.nhatdang.entity.SlangWord;
import com.nhatdang.view.form.FindForm;
import com.nhatdang.view.form.IForm;

public class HistoryView implements IView {


	//List of history data
	private List<SlangWord> historyData;
	
	public HistoryView() {
		historyData = null;
	}
	
	//Set the answers after finding
	public IView setHistoryData(List<SlangWord> historyData) {
		this.historyData = historyData;
		return this;
	}
	
	@Override
	public int showExecute() {
			
		//Scanner for input
		Scanner scanner = new Scanner(System.in);
		
		//Check if there are no history data before
		if (null == historyData  || historyData .isEmpty()) {
			System.out.println("Empty history!");
		} else {
			
			//Print all the history slang word
			System.out.println("History: ");
			historyData.forEach(slangWord -> {
				System.out.println(slangWord);
			});
			
		}
		
		//Print the next instruction
		System.out.print("\nPress enter to continue: ");
		scanner.nextLine();
		
		//Just need to hit enter, to return to input word/keyword form
		//	=> never get error
		return NO_ERROR_CODE;
	}
}
