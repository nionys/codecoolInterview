package com.codecool.samu.codecoolinterview.jacksonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    private String dimension;
    private int percentage;

    public Result(
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
