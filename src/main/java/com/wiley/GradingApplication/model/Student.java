package com.wiley.GradingApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "STUDENT")
public class Student  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentAge")
    private Integer studentAge;

}
