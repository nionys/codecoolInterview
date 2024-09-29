package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "held_exam")
public class HeldExam {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Exam exam;
    private boolean successful;

    public HeldExam() {}
    public HeldExam(Exam exam, boolean successful) {
        this.exam = exam;
        this.successful = successful;
    }
}
