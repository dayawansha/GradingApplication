package com.wiley.GradingApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewQuesDto {
    private String questionDes;
    private Integer time;
    private Integer attempts;
    private String ansStatus;

}
