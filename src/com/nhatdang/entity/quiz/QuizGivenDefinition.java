package com.nhatdang.entity.quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizGivenDefinition extends StringQuiz {

	
	public QuizGivenDefinition() {
		//do nothing
	}
	
	public QuizGivenDefinition(Quiz quiz) {
		
		//Get the answer index
		answerIndex = quiz.getAnswerIndex();
		
		//Get the definitions from the options
		options = quiz
				.getOptions()
				.stream()
				.map(slangWord -> slangWord.getWord())
				.collect(Collectors.toList());
		
		//Get the word from the given
		given = quiz.getGiven().getDefinition();
		
	}

}
