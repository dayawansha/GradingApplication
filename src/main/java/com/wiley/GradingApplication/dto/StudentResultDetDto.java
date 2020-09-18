package com.wiley.GradingApplication.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResultDetDto {

    private String description;
    private Integer numOfQues;
    private Integer weight;
    private Integer marks;

}
