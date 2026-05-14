package com.monolithic.quizApp.repo;

import com.monolithic.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}
