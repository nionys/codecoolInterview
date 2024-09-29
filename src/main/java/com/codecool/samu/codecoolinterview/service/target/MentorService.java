package com.codecool.samu.codecoolinterview.service.target;


import com.codecool.samu.codecoolinterview.dbTarget.model.Mentor;
import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.repository.MentorRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.PersonRepository;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {
    private PersonService personService;
    private MentorRepository mentorRepository;
    private PersonRepository personRepository;

    public MentorService(PersonService personService, MentorRepository mentorRepository, PersonRepository personRepository) {
        this.personService = personService;
        this.mentorRepository = mentorRepository;
        this.personRepository = personRepository;
    }

    public List<Person> getAllMentorsAsPersons() {
        return mentorRepository.findAll().stream()
            .map(Mentor::getPerson)
            .toList();
    }

    public Mentor getMentorById(long id) {
        return mentorRepository.findById(id)
            .orElseThrow(() -> new NoSuchStudentException(id));
    }

    public Mentor getMentorByEmail(String email) {
        return mentorRepository.findByPersonEmail(email)
            .orElseThrow(() -> new NoSuchStudentException(email));
    }

    public long promoteToMentor(String email) {
        Person person = personService.findByEmail(email);
        return promoteToMentor(person);
    }

    public long createMentor(Person person) {
        long personId = personService.addUser(person);
        return promoteToMentor(person);
    }

    private long promoteToMentor(Person person) {
        Mentor student = mentorRepository.save(new Mentor(person));
        return student.getId();
    }
}
