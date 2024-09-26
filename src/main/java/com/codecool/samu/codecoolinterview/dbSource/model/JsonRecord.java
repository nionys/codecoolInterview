package com.codecool.samu.codecoolinterview.dbSource.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class JsonRecord{
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime time;
    private String json;

    public JsonRecord() {}
    public JsonRecord(long id, LocalDateTime time, String json) {
        this.id = id;
        this.time = time;
        this.json = json;
    }

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
