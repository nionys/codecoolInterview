package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "held_exam")
public class HeldExam {
    @Id
    @OneToOne
    private Exam exam;
    private boolean successful;
}
