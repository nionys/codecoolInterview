package com.codecool.samu.codecoolinterview.controller.target;

import com.codecool.samu.codecoolinterview.model.entity.target.Person;
import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
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
    public List<PersonDto> findAllPersons() {
        return personService.findAllPersons();
    }

    @PostMapping("")
    public long addPerson(@RequestBody PersonDto person) {
        return personService.addPerson(person);
    }

    @GetMapping("/id/{id}")
    public PersonDto findPersonById(@PathVariable long id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/email/{email}")
    public PersonDto findPersonByEmail(@PathVariable String email) {
        return personService.findPersonByEmail(email);
    }
}
