package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dto.PersonDto;
import com.codecool.samu.codecoolinterview.service.target.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/person")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @PostMapping("")
    public long addPerson(@RequestBody PersonDto person) {
        return personService.addPerson(person);
    }

    @GetMapping("/id/{id}")
    public Person findPersonById(@PathVariable long id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/email/{email}")
    public Person findPersonByEmail(@PathVariable String email) {
        return personService.findPersonByEmail(email);
    }
}
