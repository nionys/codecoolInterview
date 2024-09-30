package com.codecool.samu.codecoolinterview.model.entity.target;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "result", uniqueConstraints = { @UniqueConstraint(columnNames = { "heldExam", "dimension" }) })
public class Result {
    @Id
    @GeneratedValue
    private long id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private HeldExam heldExam;
    private String dimension;
    private int percentage;

    public Result() {}
    public Result(HeldExam heldExam, String dimension, int percentage) {
        this.heldExam = heldExam;
        this.dimension = dimension;
        this.percentage = percentage;
    }

    public String getDimension() {
        return dimension;
    }

    public int getPercentage() {
        return percentage;
    }
}
