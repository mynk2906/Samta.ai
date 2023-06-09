package com.samta.ai.Model;


public class NextQuestionResponseDTO {

    private String correctAnswer;
    private QuestionDTO nextQuestion;
	
    public NextQuestionResponseDTO() {
		super();
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public QuestionDTO getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(QuestionDTO nextQuestion) {
		this.nextQuestion = nextQuestion;
	}
    
    
    
    
}
