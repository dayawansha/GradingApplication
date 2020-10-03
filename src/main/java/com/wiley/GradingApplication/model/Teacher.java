package com.wiley.GradingApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "TEACHER")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherId;

    @Column(name = "teacherName")
    private String teacherName;

    @Column(name = "teacherAge")
    private Integer teacherAge;

}
