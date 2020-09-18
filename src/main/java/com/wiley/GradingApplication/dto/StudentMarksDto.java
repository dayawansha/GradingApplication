package com.wiley.GradingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentMarksDto {

    private String studentName;
    private double wholeMark ;
}
