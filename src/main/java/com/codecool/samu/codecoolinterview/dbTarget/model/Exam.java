package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Mentor mentor;
    private String module;
    private LocalDate date;
    private String comment;
}
