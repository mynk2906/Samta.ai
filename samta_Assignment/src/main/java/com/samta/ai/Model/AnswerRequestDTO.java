package com.samta.ai.Model;


public class AnswerRequestDTO {
	
	 private Long questionId;
	 private String answer;
	
	 public AnswerRequestDTO() {
		super();
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	 
	 
	 
}
