package com.wiley.GradingApplication.controller;

import com.wiley.GradingApplication.service.TeacherManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacherManage")
@Slf4j
@CrossOrigin
public class TeacherManageController {

    @Autowired
    private TeacherManageService teacherManageService;


}
