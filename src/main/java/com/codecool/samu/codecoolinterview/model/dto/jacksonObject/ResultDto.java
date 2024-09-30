package com.codecool.samu.codecoolinterview.model.dto.jacksonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDto {
    private String dimension;
    private int percentage;

    public ResultDto(
        @JsonProperty(value = "dimension", required = true) String dimension,
        @JsonProperty(value = "result", required = true) int percentage) {
        this.dimension = dimension;
        this.percentage = percentage;
    }

    public String getDimension() {
        return dimension;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
