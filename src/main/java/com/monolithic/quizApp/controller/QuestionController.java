package com.monolithic.quizApp.controller;

import com.monolithic.quizApp.model.Question;
import com.monolithic.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<?> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        if(questions != null){
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("category/{value}")
    public List<Question> getQuestionsbyCategory(@PathVariable String value){
        return questionService.getQuestionsbyCategory(value);
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question){
        String message = "";
        try {
            questionService.addQuestion(question);
            message = "success";
        }
        catch(Exception e){
            message = e.getMessage();
        }
        return message;
    }
}
