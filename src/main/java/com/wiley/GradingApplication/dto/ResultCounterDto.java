package com.wiley.GradingApplication.dto;

import lombok.Data;

@Data
public class ResultCounterDto {

    private Integer correctAns;
    private Integer incorrectAns;
    private Integer partialCorrectAns;
}
