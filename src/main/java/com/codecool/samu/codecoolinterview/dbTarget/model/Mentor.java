package com.codecool.samu.codecoolinterview.dbTarget.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mentos")
public class Mentor {
    @Id
    @OneToOne
    private User user;
}
