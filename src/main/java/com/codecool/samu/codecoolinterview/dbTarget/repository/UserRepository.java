package com.codecool.samu.codecoolinterview.dbTarget.repository;

import com.codecool.samu.codecoolinterview.dbTarget.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
