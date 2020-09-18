package com.wiley.GradingApplication.controller;


import com.wiley.GradingApplication.dto.common.GenericResponse;
import com.wiley.GradingApplication.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@Slf4j
@CrossOrigin
public class ResultController {

    @Autowired
    private ResultService resultService;


    @GetMapping("/student/{studentId}/assignment/{assignmentId}/course/{courseId}")
    public ResponseEntity<GenericResponse> getResultForStudent(@PathVariable int studentId,
                                                               @PathVariable int assignmentId,
                                                               @PathVariable int courseId) throws Exception {
        log.info("studentId: " + studentId);
        log.info("assignmentId: " + assignmentId);
        log.info("courseId: " + courseId);
        ResponseEntity<GenericResponse> responseEntity = resultService.getResultForStudent( studentId,  assignmentId,  courseId);
        log.info("Response: " + responseEntity.getBody());
        return responseEntity;
    }


    @GetMapping("review/student/{studentId}/assignment/{assignmentId}/course/{courseId}")
    public ResponseEntity<GenericResponse> getQuestionsForReview(@PathVariable int studentId,
                                                               @PathVariable int assignmentId,
                                                               @PathVariable int courseId) throws Exception {
        log.info("studentId: " + studentId);
        log.info("assignmentId: " + assignmentId);
        log.info("courseId: " + courseId);
        ResponseEntity<GenericResponse> responseEntity = resultService.getQuestionsForReview( studentId,  assignmentId,  courseId);
        log.info("Response: " + responseEntity.getBody());
        return responseEntity;
    }


    @GetMapping("statistics/teacher/{teacherId}/assignment/{assignmentId}/course/{courseId}")
    public ResponseEntity<GenericResponse> getStaticsForQuestions(@PathVariable int teacherId,
                                                               @PathVariable int assignmentId,
                                                               @PathVariable int courseId) throws Exception {
        log.info("teacher: " + teacherId);
        log.info("assignmentId: " + assignmentId);
        log.info("courseId: " + courseId);
        ResponseEntity<GenericResponse> responseEntity = resultService.getStaticsForQuestions( teacherId,  assignmentId,  courseId);
        log.info("Response: " + responseEntity.getBody());
        return responseEntity;
    }


    @GetMapping("grades/teacher/{teacherId}/assignment/{assignmentId}/course/{courseId}")
    public ResponseEntity<GenericResponse> getOverallGrade(@PathVariable int teacherId,
                                                               @PathVariable int assignmentId,
                                                               @PathVariable int courseId) throws Exception {
        log.info("teacher: " + teacherId);
        log.info("assignmentId: " + assignmentId);
        log.info("courseId: " + courseId);
        ResponseEntity<GenericResponse> responseEntity = resultService.getOverallGrade( teacherId,  assignmentId,  courseId);
        log.info("Response: " + responseEntity.getBody());
        return responseEntity;
    }


}
