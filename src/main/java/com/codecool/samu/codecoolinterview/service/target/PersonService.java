package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.exception.DuplicateEmailException;
import com.codecool.samu.codecoolinterview.exception.NoSuchPersonException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public long addUser(Person person) {
        Person savedPerson;
        try {
            savedPerson = personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException(person.getEmail());
        }
        return savedPerson.getId();
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person findById(long id) {
        return personRepository.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email).orElseThrow(() -> new NoSuchPersonException(email));
    }
}
