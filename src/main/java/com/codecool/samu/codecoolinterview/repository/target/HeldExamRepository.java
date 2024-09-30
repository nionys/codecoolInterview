package com.codecool.samu.codecoolinterview.repository.target;

import com.codecool.samu.codecoolinterview.model.entity.target.HeldExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeldExamRepository extends JpaRepository<HeldExam, Long> {
    Optional<HeldExam> findByExamId(Long id);
}
