package com.codecool.samu.codecoolinterview.repository.target;

import com.codecool.samu.codecoolinterview.model.entity.target.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
