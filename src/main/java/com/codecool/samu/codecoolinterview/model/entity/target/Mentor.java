package com.codecool.samu.codecoolinterview.model.entity.target;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @GeneratedValue
    private long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
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
