package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private User user;

    public Student() {}

    public Student(User user) {
        this.user = user;
    }

    public long getId() {
        return user.getId();
    }

}
