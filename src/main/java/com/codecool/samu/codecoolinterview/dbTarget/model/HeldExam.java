package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

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

    public long getId() {
        return id;
    }

    public Exam getExam() {
        return exam;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
