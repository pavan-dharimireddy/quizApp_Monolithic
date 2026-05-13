package com.monolithic.quizApp.service;

import com.monolithic.quizApp.model.Question;
import com.monolithic.quizApp.repo.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
        return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsbyCategory(String value) {
        return questionDAO.findByCategory(value);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        String message = "";
        try {
            questionDAO.save(question);
            message = "success";
        }
        catch(Exception e){
            message = e.getMessage();
        }
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
