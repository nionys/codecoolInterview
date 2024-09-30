package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
import com.codecool.samu.codecoolinterview.service.source.SourceService;
import com.codecool.samu.codecoolinterview.service.target.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InitializationController {
    private final SourceService sourceService;
    private final PersonService personService;
    private final MentorService mentorService;
    private final StudentService studentService;
    private final ExamService examService;
    private final HeldExamService heldExamService;
    private final ResultService resultService;

    public InitializationController(
        SourceService sourceService,
        PersonService personService,
        MentorService mentorService,
        StudentService studentService,
        ExamService examService,
        HeldExamService heldExamService,
        ResultService resultService
    ) {
        this.sourceService = sourceService;
        this.personService = personService;
        this.mentorService = mentorService;
        this.studentService = studentService;
        this.examService = examService;
        this.heldExamService = heldExamService;
        this.resultService = resultService;
    }


    @PostConstruct
    public void initSourceDb() {
        sourceService.deleteAll();
        sourceService.addRecord("{ \"module\": \"ProgBasics\", \"mentor\": \"peter.szarka@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-02-01\", \"cancelled\": true, \"comment\": \"Foo was sick.\" }");
        sourceService.addRecord("{ \"module\": \"ProgBasics\", \"mentor\": \"peter.szarka@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-02-05\", \"cancelled\": false, \"success\": true, \"comment\": \"Everything was ok.\", \"results\": [{ \"dimension\": \"Coding\", \"result\": 80 }, { \"dimension\": \"Communication\", \"result\": 100}] }");
        sourceService.addRecord("{ \"module\": \"Web\", \"mentor\": \"mano.fabian@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-05-11\", \"cancelled\": false, \"success\": false, \"comment\": \"Couldn't really start, just wrote some HTML.\", \"results\": [{ \"dimension\": \"Coding\", \"result\": 0 }, {\"dimension\": \"HTML\", \"result\": 30}, {\"dimension\": \"Communication\", \"result\": 30}] }");
        sourceService.addRecord("{ \"module\": \"Web\", \"mentor\": \"peter.szarka@codecool.com\", \"student\": \"foo@bar.com\", \"date\": \"2024-05-21\", \"cancelled\": false, \"success\": false, \"comment\": \"Wrote spaghetti code, and tried to sell it. Nice page, though.\", \"results\": [{ \"dimension\": \"Coding\", \"result\": 20 }, {\"dimension\": \"HTML\", \"result\": 100}, {\"dimension\": \"Communication\", \"result\": 80}] }");

        resultService.deleteAll();
        heldExamService.deleteAll();
        examService.deleteAll();
        mentorService.deleteAll();
        studentService.deleteAll();
        personService.deleteAll();
        mentorService.createMentor(new PersonDto("Szarka Péter", "peter.szarka@codecool.com"));
        mentorService.createMentor(new PersonDto("Fábián Manó", "mano.fabian@codecool.com"));
        studentService.createStudent(new PersonDto("Samu", "foo@bar.com"));
    }
}
