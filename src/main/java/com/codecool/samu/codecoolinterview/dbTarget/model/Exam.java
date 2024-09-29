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

    public Exam() {}
    public Exam(Student student, Mentor mentor, String module, LocalDate date, String comment) {
        this.student = student;
        this.mentor = mentor;
        this.module = module;
        this.date = date;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

}
