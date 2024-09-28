package com.codecool.samu.codecoolinterview.jacksonObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Exam {
    private String module;
    private String mentor;
    private String student;
    private Date date;
    private boolean cancelled;
    private Boolean success = null;
    private List<Result> results = null;
    private String comment;

    @JsonCreator
    public Exam(
        @JsonProperty(value = "module", required = true) String module,
        @JsonProperty(value = "mentor", required = true) String mentor,
        @JsonProperty(value = "student", required = true) String student,
        @JsonProperty(value = "date", required = true) Date date,
        @JsonProperty(value = "cancelled", required = true) boolean cancelled,
        @JsonProperty(value = "comment", required = true) String comment,
        @JsonProperty(value = "success") Boolean success,
        @JsonProperty(value = "results") List<Result> results) {
        if (cancelled) {
            if (success != null) throw new IllegalArgumentException("Cancelled record has success field");
            if (results != null) throw new IllegalArgumentException("Cancelled record has results field");
        }
        this.module = module;
        this.mentor = mentor;
        this.student = student;
        this.date = date;
        this.cancelled = cancelled;
        this.success = success;
        this.results = results;
        this.comment = comment;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
