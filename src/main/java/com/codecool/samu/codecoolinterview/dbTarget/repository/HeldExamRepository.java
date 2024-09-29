package com.codecool.samu.codecoolinterview.dbTarget.repository;

import com.codecool.samu.codecoolinterview.dbTarget.model.Exam;
import com.codecool.samu.codecoolinterview.dbTarget.model.HeldExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeldExamRepository extends JpaRepository<HeldExam, Long> {
    Optional<HeldExam> findByExamId(Long id);
}
