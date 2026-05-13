package com.monolithic.quizApp.service;

import com.monolithic.quizApp.model.Question;
import com.monolithic.quizApp.repo.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestions() {
        return questionDAO.findAll();
    }

    public List<Question> getQuestionsbyCategory(String value) {
        return questionDAO.findByCategory(value);
    }

    public void addQuestion(Question question) {
        questionDAO.save(question);
    }
}
