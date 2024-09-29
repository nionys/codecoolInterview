package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dto.MentorDto;
import com.codecool.samu.codecoolinterview.dto.NewMentorDto;
import com.codecool.samu.codecoolinterview.dto.PersonDto;
import com.codecool.samu.codecoolinterview.service.target.MentorService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/mentor")
public class MentorController {
    private final MentorService mentorService;
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/promote")
    public long promoteToMentor(@RequestBody String email) {
        return mentorService.promoteToMentor(email);
    }

    @GetMapping("/all")
    public List<MentorDto> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @GetMapping("/id/{id}")
    public MentorDto getMentorById(@PathVariable long id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping("")
    public NewMentorDto createMentor(@RequestBody PersonDto person) {
        return mentorService.createMentor(person);
    }
}