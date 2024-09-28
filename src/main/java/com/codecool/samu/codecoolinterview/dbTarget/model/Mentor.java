package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Person person;

    public Mentor() {}
    public Mentor(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }
}
