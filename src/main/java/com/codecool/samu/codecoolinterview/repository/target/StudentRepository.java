package com.codecool.samu.codecoolinterview.repository.target;

import com.codecool.samu.codecoolinterview.model.entity.target.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByPersonId(long id);
    Optional<Student> findByPersonEmail(String email);
}
