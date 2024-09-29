package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.dto.PersonDto;
import com.codecool.samu.codecoolinterview.exception.DuplicateEmailException;
import com.codecool.samu.codecoolinterview.exception.NoSuchPersonException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public long addPerson(PersonDto personDto) {
        Person person = convertToNewPerson(personDto);
        Person savedPerson;
        try {
            savedPerson = personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException(personDto.email());
        }
        return savedPerson.getId();
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person findPersonById(long id) {
        return personRepository.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
    }

    public Person findPersonByEmail(String email) {
        return personRepository.findByEmail(email).orElseThrow(() -> new NoSuchPersonException(email));
    }

    public Person convertToExistingPerson(long personId, PersonDto personDto) {
        return new Person(personId, personDto.name(), personDto.email());
    }
    public Person convertToNewPerson(PersonDto personDto) {
        return new Person(personDto.name(), personDto.email());
    }
}
