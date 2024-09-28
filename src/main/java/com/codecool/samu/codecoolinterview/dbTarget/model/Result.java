package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "result", uniqueConstraints = { @UniqueConstraint(columnNames = { "heldExam", "dimension" }) })
public class Result {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private HeldExam heldExam;
    private String dimension;
    private String percentage;
}
