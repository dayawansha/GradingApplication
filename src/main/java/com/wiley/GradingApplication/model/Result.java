package com.wiley.GradingApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "RESULT")
public class Result  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resultId;

    @Column(name = "result")
    private String result;

    @Column(name = "timeSpent")
    private Integer timeSpent;

    @Column(name = "numberOfAttempts")
    private Integer numberOfAttempts;

    @JoinColumn(name = "QUESTION", referencedColumnName = "questionId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Question question;

    @JoinColumn(name = "STUDENT", referencedColumnName = "studentId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Student student;

    @JoinColumn(name = "TEACHER", referencedColumnName = "teacherId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Teacher teacher;


}


