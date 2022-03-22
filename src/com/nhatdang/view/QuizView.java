package com.nhatdang.view;

import java.util.List;
import java.util.Scanner;

import com.nhatdang.view.form.QuizForm;
import com.nhatdang.view.form.QuizForm.QuizType;

public class QuizView implements IView {

	//Type of the quiz
	private QuizType type;
	
	//Form of the quiz
	private QuizForm quizForm;
	
	//List of answers for the user
	private List<String> answers;
	
	//The question from the quiz
	private String given;
	
	//The index of the correct answer
	private int correctAnswerIndex;
	
	//Check if the asnwer is correct
	private boolean isCorrect;
	
	public QuizView(QuizType type) {
		this.type = type;
		quizForm = new QuizForm(type);
	}
	
	//Setters
	public QuizView setAnswers(List<String> answers) {
		this.answers = answers;
		quizForm.setAnswer(answers);
		return this;
	}

	public QuizView setGiven(String given) {
		this.given = given;
		quizForm.setGiven(given);
		return this;
	}

	public QuizView setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex;
		return this;
	}
	
	public QuizView setCorrectState(boolean isCorrect) {
		this.isCorrect = isCorrect;
		return this;
	}

	//Getter for quiz form
	public QuizForm getQuizForm() {return quizForm;}
	
	//Text if the answer is correct
	private String correctText() {
		return "Correct!";
	}
	
	
	//Text if the answer is wrong
	private String failedText() {
		
		//Get the true index
		int oneIndexingIndex = correctAnswerIndex + 1;
		
		return "Incorrect! The answer is: " + oneIndexingIndex + ") " + answers.get(oneIndexingIndex - 1);
	}
	
	@Override
	public int showExecute() {
	
		//Print the correction text
		if (isCorrect) {
			System.out.println(correctText());
		}
		else {System.out.println(failedText());}
		
		//Print the next instruction
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPress enter to the next quiz: ");
		scanner.nextLine();
		
		return NO_ERROR_CODE;
	}
}
