package com.codecool.samu.codecoolinterview.service.target;


import com.codecool.samu.codecoolinterview.model.entity.target.Mentor;
import com.codecool.samu.codecoolinterview.model.entity.target.Person;
import com.codecool.samu.codecoolinterview.repository.target.MentorRepository;
import com.codecool.samu.codecoolinterview.model.dto.MentorDto;
import com.codecool.samu.codecoolinterview.model.dto.NewMentorDto;
import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
import com.codecool.samu.codecoolinterview.exception.NoSuchMentorException;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorService {
    private final PersonService personService;
    private final MentorRepository mentorRepository;

    public MentorService(PersonService personService, MentorRepository mentorRepository) {
        this.personService = personService;
        this.mentorRepository = mentorRepository;
    }

    public void deleteAll() {
        mentorRepository.deleteAll();
    }

    public List<MentorDto> getAllMentors() {
        return mentorRepository.findAll().stream()
            .map(this::convertToMentorDto)
            .toList();
    }

    public MentorDto getMentorById(long id) {
        Mentor mentor = mentorRepository.findById(id)
            .orElseThrow(() -> new NoSuchStudentException(id));
        return convertToMentorDto(mentor);
    }

    public Mentor getMentorByEmail(String email) {
        return mentorRepository.findByPersonEmail(email)
            .orElseThrow(() -> new NoSuchMentorException(email));
    }

    public long promoteToMentor(String email) {
        Person person = personService.findPersonByEmail(email);
        return promoteToMentor(person);
    }

    public NewMentorDto createMentor(PersonDto personDto) {
        long personId = personService.addPerson(personDto);
        Person person = personService.convertToExistingPerson(personId, personDto);
        long mentorId = promoteToMentor(person);
        return new NewMentorDto(personId, mentorId);
    }

    private long promoteToMentor(Person person) {
        Mentor student = mentorRepository.save(new Mentor(person));
        return student.getId();
    }

    public MentorDto convertToMentorDto(Mentor mentor) {
        Person person = mentor.getPerson();
        return new MentorDto(
            person.getId(),
            mentor.getId(),
            person.getName(),
            person.getEmail()
        );
    }
}
