package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Person person;

    public Student() {}

    public Student(Person person) {
        this.person = person;
    }

    public long getId() {
        return person.getId();
    }

    public Person getPerson() {
        return person;
    }
}
