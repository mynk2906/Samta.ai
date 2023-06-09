package com.samta.ai.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samta.ai.Exceptions.QuestionException;
import com.samta.ai.Model.NextQuestionResponseDTO;
import com.samta.ai.Model.Question;
import com.samta.ai.Model.QuestionDTO;
import com.samta.ai.Model.RandomQuestion;
import com.samta.ai.Repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private final QuestionRepository questionRepository ;
	
	
	public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> fetchRandomQuestions(int count) {
		
    	String url="https://jservice.io/api/random?count="+count;
		RestTemplate restTemplate=new RestTemplate();
		RandomQuestion[] randomQuestions = restTemplate.getForObject(url,RandomQuestion[].class);
    	
		for(RandomQuestion rq:randomQuestions) {
			Question question=new Question();
			question.setQuestionId(rq.getId());
			question.setQuestion(rq.getQuestion());
			question.setAnswer(rq.getAnswer());
			
			questionRepository.save(question);
			
		}
		
		List<Question> questions=questionRepository.findAll();
    	
    	return questions;
    }

    public QuestionDTO getQuestionById(Long questionId) throws QuestionException {
		
    	Optional<Question> opt = questionRepository.findById(questionId);
    	
    	if(!opt.isPresent()) throw new QuestionException("Question Id not found");
    	
    	Question question=opt.get();
    	
    	QuestionDTO questionDTO=new QuestionDTO();
    	questionDTO.setQuestionId(questionId);
    	questionDTO.setQuestion(question.getQuestion());
    	
    	return questionDTO;
    	
    }

    public NextQuestionResponseDTO checkAnswerAndGetNextQuestion(Long questionId, String answer) throws QuestionException {
		
    	Optional<Question> opt = questionRepository.findById(questionId);
    	
    	if(!opt.isPresent()) throw new QuestionException("Question Id not found");
    	
    	Question question=opt.get();
    	
    	if(!question.getAnswer().equals(answer)) {
    		throw new QuestionException("answere found different");
    	}
    	
    	
    	Question randomQuestion=randomQuestion();
    	
    	QuestionDTO questionDTO=new QuestionDTO();
    	questionDTO.setQuestionId(randomQuestion.getQuestionId());
    	questionDTO.setQuestion(randomQuestion.getQuestion());
    	
    	NextQuestionResponseDTO nextQuestionResponseDTO=new NextQuestionResponseDTO();
    	nextQuestionResponseDTO.setCorrectAnswer(answer);
    	nextQuestionResponseDTO.setNextQuestion(questionDTO);
    	
    	return nextQuestionResponseDTO;
    }
	
    
    public Question randomQuestion() {
    	
    	List<Question> list=questionRepository.findAll();
    	Random random=new Random();
    	int randomNum=random.nextInt(list.size());
    	
    	Question randomQuestion=list.get(randomNum);
    	
    	return randomQuestion;
    }
	
	
}
