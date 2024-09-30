package com.codecool.samu.codecoolinterview.model.dto.jacksonObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ParsedExamDto {
    private String module;
    private String mentorEmail;
    private String studentEmail;
    private Date date;
    private boolean cancelled;
    private Boolean success = null;
    private List<ResultDto> resultDtos = null;
    private String comment;

    @JsonCreator
    public ParsedExamDto(
        @JsonProperty(value = "module", required = true) String module,
        @JsonProperty(value = "mentor", required = true) String mentorEmail,
        @JsonProperty(value = "student", required = true) String studentEmail,
        @JsonProperty(value = "date", required = true) Date date,
        @JsonProperty(value = "cancelled", required = true) boolean cancelled,
        @JsonProperty(value = "comment", required = true) String comment,
        @JsonProperty(value = "success") Boolean success,
        @JsonProperty(value = "results") List<ResultDto> resultDtos) {
        if (cancelled) {
            if (success != null) throw new IllegalArgumentException("Cancelled exam record has success field");
            if (resultDtos != null) throw new IllegalArgumentException("Cancelled exam record has results field");
        }
        this.module = module;
        this.mentorEmail = mentorEmail;
        this.studentEmail = studentEmail;
        this.date = date;
        this.cancelled = cancelled;
        this.success = success;
        this.resultDtos = resultDtos;
        this.comment = comment;
    }
    public boolean isHeld() {
        return !cancelled;
    }


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
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

    public List<ResultDto> getResults() {
        return resultDtos;
    }

    public void setResults(List<ResultDto> resultDtos) {
        this.resultDtos = resultDtos;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
