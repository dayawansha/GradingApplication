package com.wiley.GradingApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ASSIGNMENT")
public class Assignment  implements Serializable {

    @Id
    @Column(name = "assignmentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer assignmentId;

    @Column(name = "assignmentName")
    private String assignmentName;

    @JoinColumn(name = "COURSE", referencedColumnName = "courseId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "STUDENT", referencedColumnName = "studentId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "TEACHER", referencedColumnName = "teacherId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Teacher teacher;



}

