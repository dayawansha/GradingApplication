package com.wiley.GradingApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "COURSE")
public class Course  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column(name = "courseName")
    private String courseName;

    @JoinColumn(name = "STUDENT", referencedColumnName = "studentId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Student student;

    @JoinColumn(name = "TEACHER", referencedColumnName = "teacherId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Teacher teacher;

}

