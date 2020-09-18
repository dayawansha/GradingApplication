package com.wiley.GradingApplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiley.GradingApplication.dto.common.DropDownDto;
import com.wiley.GradingApplication.dto.common.GenericResponse;
import com.wiley.GradingApplication.model.Course;
import com.wiley.GradingApplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<GenericResponse> getAllCourses() throws Exception {

        List<DropDownDto> courseDtoList= new ArrayList<>();


        List<Course> dataMap = courseRepository.findAll();
        for(Course course  : dataMap){
//            CourseDto courseDto = objectMapper.convertValue(course, CourseDto.class);
            DropDownDto dropDownDto = new DropDownDto();
            dropDownDto.setId(course.getCourseId());
            dropDownDto.setDescription(course.getCourseName());
            courseDtoList.add(dropDownDto);
        }

        GenericResponse genericResponse = new GenericResponse(courseDtoList);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
