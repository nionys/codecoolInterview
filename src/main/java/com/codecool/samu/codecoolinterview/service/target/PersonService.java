package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.model.entity.target.Person;
import com.codecool.samu.codecoolinterview.repository.target.PersonRepository;
import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
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

    public void deleteAll() {
        personRepository.deleteAll();
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

    public List<PersonDto> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::convertToPersonDto).toList();
    }

    public PersonDto findPersonById(long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
        return convertToPersonDto(person);
    }

    public PersonDto findPersonByEmail(String email) {
        Person person = personRepository.findByEmail(email).orElseThrow(() -> new NoSuchPersonException(email));
        return convertToPersonDto(person);
    }

    public Person convertToExistingPerson(long personId, PersonDto personDto) {
        return new Person(personId, personDto.name(), personDto.email());
    }
    public Person convertToNewPerson(PersonDto personDto) {
        return new Person(personDto.name(), personDto.email());
    }

    private PersonDto convertToPersonDto(Person person) {
        return new PersonDto(person.getName(), person.getEmail());
    }
}
