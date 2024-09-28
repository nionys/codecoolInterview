package com.codecool.samu.codecoolinterview.dbTarget.repository;

import com.codecool.samu.codecoolinterview.dbTarget.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
