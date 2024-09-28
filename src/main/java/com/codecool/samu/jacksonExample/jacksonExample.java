package com.codecool.samu.jacksonExample;
import com.codecool.samu.codecoolinterview.jacksonObject.Exam;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class jacksonExample {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (String json: new String[] {
            "{ \"module\": \"ProgBasics\", \"mentor\": \"peter.szarka@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-02-01\", \"cancelled\": true, \"comment\": \"Foo was sick.\" }",
            "{ \"module\": \"ProgBasics\"}",
            "{ \"module\": \"Web\", \"mentor\": \"peter.szarka@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-05-21\", \"cancelled\": false, \"success\": false, \"comment\": \"Wrote spaghetti code, and tried to sell it. Nice page, though.\", \"results\": [{ \"dimension\": \"Coding\", \"result\": 20 }, {\"dimension\": \"HTML\", \"result\": 100}, {\"dimension\": \"Communication\", \"result\": 80}] }"
        }) {
            try {
                Exam mappedObject = objectMapper.readValue(json, Exam.class);
                System.out.println(objectMapper.writeValueAsString(mappedObject));

            } catch (JsonProcessingException e) {
                System.out.println("error");
            }

        }
    }
}
