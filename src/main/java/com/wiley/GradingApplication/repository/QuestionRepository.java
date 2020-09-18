package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Assignment;
import com.wiley.GradingApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question, Integer> {
}

