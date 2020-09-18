package com.wiley.GradingApplication.controller;


import com.wiley.GradingApplication.dto.common.GenericResponse;
import com.wiley.GradingApplication.service.AssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignment")
@Slf4j
@CrossOrigin
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllAssignments() throws Exception {
        ResponseEntity<GenericResponse> responseEntity = assignmentService.getAllAssignment();
        log.info("Response: " + responseEntity.getBody());
        return responseEntity;
    }

}
