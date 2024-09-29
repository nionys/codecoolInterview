package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "exam", uniqueConstraints = { @UniqueConstraint(columnNames = { "student", "module", "date" }) })
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

    public Student getStudent() {
        return student;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public String getModule() {
        return module;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
