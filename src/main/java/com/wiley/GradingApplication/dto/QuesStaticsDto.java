package com.wiley.GradingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuesStaticsDto {
    private String questionDes;
    private Double avgCorrect;
    private Double avgIncorrect;
    private Double avgPartially;
    private Double avgTime;
}
