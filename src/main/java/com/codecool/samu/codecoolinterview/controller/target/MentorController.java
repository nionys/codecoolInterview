package com.codecool.samu.codecoolinterview.controller.target;


import com.codecool.samu.codecoolinterview.dbTarget.model.Mentor;
import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.service.target.MentorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/mentor")
public class MentorController {
    private MentorService mentorService;
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/promote")
    public long promoteToMentor(@RequestBody String email) {
        return mentorService.promoteToMentor(email);
    }

    @GetMapping("/all")
    public List<Person> getAllMentors() {
        return mentorService.getAllMentorsAsPersons();
    }

    @GetMapping("/id/{id}")
    public Mentor getMentorById(@PathVariable long id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping("")
    public long createMentor(@RequestBody Person person) {
        return mentorService.createMentor(person);
    }
}