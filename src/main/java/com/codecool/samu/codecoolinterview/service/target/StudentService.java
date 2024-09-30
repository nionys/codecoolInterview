package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.model.entity.target.Person;
import com.codecool.samu.codecoolinterview.model.entity.target.Student;
import com.codecool.samu.codecoolinterview.repository.target.StudentRepository;
import com.codecool.samu.codecoolinterview.model.dto.NewStudentDto;
import com.codecool.samu.codecoolinterview.model.dto.PersonDto;
import com.codecool.samu.codecoolinterview.model.dto.StudentDto;
import com.codecool.samu.codecoolinterview.exception.NoSuchStudentException;
import com.codecool.samu.codecoolinterview.exception.PersonIsAlreadyAStudentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final PersonService personService;
    private final StudentRepository studentRepository;

    public StudentService(PersonService personService, StudentRepository studentRepository) {
        this.personService = personService;
        this.studentRepository = studentRepository;
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<StudentDto> findAllStudents() {
       return studentRepository.findAll().stream()
           .map(this::convertToStudentDto)
           .toList();
    }

    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new NoSuchStudentException(id));
        return convertToStudentDto(student);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByPersonEmail(email)
            .orElseThrow(() -> new NoSuchStudentException(email));
    }

    public long promoteToStudent(String email) {
        Person person = personService.findPersonByEmail(email);
        return promoteToStudent(person);
    }

    public NewStudentDto createStudent(PersonDto personDto) {
        long personId = personService.addPerson(personDto);
        Person person = personService.convertToExistingPerson(personId, personDto);
        long studentId = promoteToStudent(person);
        return new NewStudentDto(personId, studentId);
    }

    private long promoteToStudent(Person person) {
        Student student;
        try {
            student = studentRepository.save(new Student(person));
        } catch (DataIntegrityViolationException e) {
            throw new PersonIsAlreadyAStudentException(person.getEmail());
        }
        return student.getId();
    }

    public StudentDto convertToStudentDto(Student student) {
        Person person = student.getPerson();
        return new StudentDto(
            person.getId(),
            student.getId(),
            person.getName(),
            person.getEmail()
        );
    }
}
