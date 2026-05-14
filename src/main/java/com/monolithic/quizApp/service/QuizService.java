package com.monolithic.quizApp.service;

import com.monolithic.quizApp.model.Question;
import com.monolithic.quizApp.model.QuestionWrapper;
import com.monolithic.quizApp.model.Quiz;
import com.monolithic.quizApp.model.Response;
import com.monolithic.quizApp.repo.QuestionDAO;
import com.monolithic.quizApp.repo.QuizDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return  new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getquiz(int id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Question q : questionsfromDB){
            QuestionWrapper qp = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qp);
        }
        return  new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i=0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);

    }
}
