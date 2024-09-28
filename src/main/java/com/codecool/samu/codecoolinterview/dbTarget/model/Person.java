package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(unique = true)
    private String email;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
