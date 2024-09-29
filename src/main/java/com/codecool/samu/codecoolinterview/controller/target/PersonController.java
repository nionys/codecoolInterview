package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.service.target.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/person")
public class PersonController {
    private PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getAllUsers() {
        return personService.findAllPersons();
    }

    @PostMapping("")
    public long addStudent(@RequestBody Person person) {
        return personService.addUser(person);
    }

    @GetMapping("/id/{id}")
    public Person getStudentById(@PathVariable long id) {
        return personService.findById(id);
    }

    @GetMapping("/email/{email}")
    public Person getStudentByEmail(@PathVariable String email) {
        return personService.findByEmail(email);
    }
}
