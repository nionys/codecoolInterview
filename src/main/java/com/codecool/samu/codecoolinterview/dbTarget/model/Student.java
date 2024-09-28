package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @OneToOne
    private User user;

    public long getId() {
        return user.getId();
    }

}
