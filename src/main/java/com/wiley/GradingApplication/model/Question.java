package com.wiley.GradingApplication.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "QUESTION")
public class Question  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    @Column(name = "questionDes")
    private String questionDes;

    @JoinColumn(name = "ASSIGNMENT", referencedColumnName = "assignmentId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Assignment assignment;

}
