package com.codecool.samu.codecoolinterview.repository.target;

import com.codecool.samu.codecoolinterview.model.entity.target.HeldExam;
import com.codecool.samu.codecoolinterview.model.entity.target.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByHeldExam(HeldExam heldExam);
}
