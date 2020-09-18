package com.wiley.GradingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeDetDto {

    private String grade;
    private Integer numOfGrades;

}
