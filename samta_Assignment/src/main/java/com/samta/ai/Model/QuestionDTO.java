package com.samta.ai.Model;

public class QuestionDTO {
	
	private Long questionId;
    private String question;
	
    public QuestionDTO() {
		super();
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
    
    
}
