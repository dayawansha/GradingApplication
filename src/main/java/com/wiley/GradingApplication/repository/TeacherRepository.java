package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Assignment;
import com.wiley.GradingApplication.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository  extends JpaRepository<Teacher, Integer> {
}


