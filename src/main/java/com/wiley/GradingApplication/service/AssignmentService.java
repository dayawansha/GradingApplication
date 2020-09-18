package com.wiley.GradingApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiley.GradingApplication.dto.common.DropDownDto;
import com.wiley.GradingApplication.dto.common.GenericResponse;
import com.wiley.GradingApplication.model.Assignment;
import com.wiley.GradingApplication.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getAllAssignment() throws Exception {

        List<DropDownDto> assignmentDtoLit = new ArrayList<>();
        List<Assignment> assignmentList =assignmentRepository.findAll();
        for(Assignment assignment : assignmentList){
            DropDownDto dropDownDto = new DropDownDto();
            dropDownDto.setId(assignment.getAssignmentId());
            dropDownDto.setDescription(assignment.getAssignmentName());
            assignmentDtoLit.add(dropDownDto);
        }

        GenericResponse genericResponse = new GenericResponse(assignmentDtoLit);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
