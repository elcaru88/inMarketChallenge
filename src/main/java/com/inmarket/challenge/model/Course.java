package com.inmarket.challenge.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long courseCode;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
