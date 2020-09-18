package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Assignment;
import com.wiley.GradingApplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course, Integer> {
}


