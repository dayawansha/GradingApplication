package com.wiley.GradingApplication.repository;

import com.wiley.GradingApplication.model.Assignment;
import com.wiley.GradingApplication.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository  extends JpaRepository<Result, Integer> {

    List<Result> findByStudent_StudentIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(int studentId, int assignmentId, int courseId);

    List<Result> findByTeacher_TeacherIdAndQuestion_Assignment_AssignmentIdAndQuestion_Assignment_Course_CourseId(int teacherId, int assignmentId, int courseId);



}


