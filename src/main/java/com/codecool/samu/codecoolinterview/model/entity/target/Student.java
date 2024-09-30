package com.codecool.samu.codecoolinterview.model.entity.target;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    private Person person;

    public Student() {}

    public Student(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }
}
