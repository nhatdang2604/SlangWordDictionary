package com.nhatdang.entity.quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizGivenWord extends StringQuiz {

	public QuizGivenWord() {
		//do nothing
	}
	
	public QuizGivenWord(Quiz quiz) {
		
		//Get the answer index
		answerIndex = quiz.getAnswerIndex();
		
		//Get the definitions from the options
		options = quiz
				.getOptions()
				.stream()
				.map(slangWord -> slangWord.getDefinition())
				.collect(Collectors.toList());
		
		//Get the word from the given
		given = quiz.getGiven().getWord();
		
	}
	
}
