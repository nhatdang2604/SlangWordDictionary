package com.nhatdang.view.form;

import java.util.List;
import java.util.Scanner;

import com.nhatdang.entity.quiz.StringQuiz;
import com.nhatdang.validator.DataValidator;

public class QuizForm implements IForm {

	//Type of quize
	public static enum QuizType {
		GIVEN_WORD, GIVEN_DEFINITION;
	}
	
	//Type of the quiz
	private QuizType type;
	
	//Flag to check, if the $back is press
	private boolean backFlag;
		
	//Model of the form: the answer index
	private Integer model;
	
	//The hold quiz
	private StringQuiz quiz;
	
	//Validator for checking the answer format
	private DataValidator validator;
	
	//Getters
	@Override public boolean getBackFlag() {return backFlag;}
	@Override public Object getModel() {return model;}
	public StringQuiz getQuiz() {return quiz;}
	
	//Setters
	@Override public void setBackFlag(boolean value) {backFlag = value;}
	@Override public void setModel(Object value) {model = (Integer) value;}
	public QuizForm setQuiz(StringQuiz quiz) {this.quiz = quiz; return this;}
	
	
	public QuizForm(QuizType type) {
		this.type = type;
		backFlag = false;
		validator = new DataValidator();
		quiz = new StringQuiz();
	}
	
	
	//Return body's text of the form
	private String getPlainBody() {
		
		StringBuilder builder = new StringBuilder();
		
		//The question part
		final String question_part1 = 
					"Given the" + (type.equals(QuizType.GIVEN_WORD)?" slang word: ":
									type.equals(QuizType.GIVEN_DEFINITION)? " definition: ":"");
		
		final String question_part2 = 
					"Which is the correct" +  
						(type.equals(QuizType.GIVEN_WORD)?" definition of the given slang word?":
						type.equals(QuizType.GIVEN_DEFINITION)? " slang word of the given definition?":"");
		
		builder.append(question_part1 + quiz.getGivenString() + "\n" + question_part2 + "\n\n");
		
		//The answers part
		List<String> options = quiz.getOptionsString();
		int size = options.size();
		for (int i = 0; i < size; ++i) {
			int idx = i + 1;
			builder.append(idx + ") " + options.get(i) + "\n");
		}
		
		builder.append("\nGive the index of the answer here: ");
		
		return builder.toString();
	}
	
	//Fill the form
	//	return index of the answer, which is parsed data from the form
	@Override
	public Integer fillForm() {
		
		while (true) {
			
			//Clear screen first
			clearScreen();
			
			//Print tips
			printTips();
			
			//Print the body of the quiz
			System.out.print(getPlainBody());
		
			//For input answer
			Scanner scanner = new Scanner(System.in);
		
			//Read the answer from user
			String buffer = scanner.nextLine().trim();
			
			//If the buffer is back symbol => set the back flag
			backFlag = validator.isBackPressed(buffer);

			//Break if the user input $back
			if (backFlag) {model = null; break;}
			
			//Else => the input must be integer and in range [1, answers.size()]
			if (validator.isInteger(buffer)) {
				if (validator.isIntegerOutOfRange(buffer, 1, quiz.getOptionsString().size())) {
					
					//Reduce the selected index by 1 for sync with the index in list
					model = Integer.parseInt(buffer) - 1;
					break;
				}
			}
				
		}
		
		return model;
	}

	
	
	


}
