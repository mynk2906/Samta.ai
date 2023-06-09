package com.samta.ai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.samta.ai.Exceptions.QuestionException;
import com.samta.ai.Model.AnswerRequestDTO;
import com.samta.ai.Model.NextQuestionResponseDTO;
import com.samta.ai.Model.Question;
import com.samta.ai.Model.QuestionDTO;
import com.samta.ai.Service.QuestionService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class QuestionController {
	
	@Autowired
	private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
	/*
	 * @GetMapping("/randomQuestions/{count}") public ResponseEntity<List<Question>>
	 * fetchRandomQuestions(@PathVariable int count){
	 * 
	 * List<Question> questions = questionService.fetchRandomQuestions(count);
	 * 
	 * return new ResponseEntity<>(questions,HttpStatus.OK);
	 * 
	 * }
	 */
    
    
    @PostConstruct
    public void fetchRandomQuestions(){
    	questionService.fetchRandomQuestions(5);
    }
    
    @GetMapping("/play")
    public ResponseEntity<QuestionDTO> play() throws QuestionException {
    	
    	Question question= questionService.randomQuestion();
    	Long id=question.getQuestionId();
    	
     	QuestionDTO qDto=questionService.getQuestionById(id);
    	
    	return new ResponseEntity<QuestionDTO>(qDto,HttpStatus.OK);
    }

    @PostMapping("/next")
    public ResponseEntity<NextQuestionResponseDTO> next(@RequestBody AnswerRequestDTO answerRequest) throws QuestionException {
    	
    	NextQuestionResponseDTO nextQuestionResponseDTO=questionService.checkAnswerAndGetNextQuestion(answerRequest.getQuestionId(), answerRequest.getAnswer());
    	
    	return new ResponseEntity<NextQuestionResponseDTO>(nextQuestionResponseDTO,HttpStatus.CREATED);
    }
    
    
}
