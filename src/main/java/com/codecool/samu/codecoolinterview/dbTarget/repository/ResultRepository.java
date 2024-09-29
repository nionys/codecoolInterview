package com.codecool.samu.codecoolinterview.dbTarget.repository;

import com.codecool.samu.codecoolinterview.dbTarget.model.HeldExam;
import com.codecool.samu.codecoolinterview.dbTarget.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByHeldExam(HeldExam heldExam);
}
