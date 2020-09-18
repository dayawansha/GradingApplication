package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository  extends JpaRepository<Assignment, Integer> {
}

