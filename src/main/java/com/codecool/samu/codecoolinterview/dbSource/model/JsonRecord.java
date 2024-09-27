package com.codecool.samu.codecoolinterview.dbSource.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class JsonRecord{
    @Id
    @GeneratedValue
    private long id;
    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @CurrentTimestamp
    private LocalDateTime time;
    private String json;

    public JsonRecord() {}

    public long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getJson() {
        return json;
    }
}
