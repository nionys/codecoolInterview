package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.Exam;
import com.codecool.samu.codecoolinterview.dbTarget.model.HeldExam;
import com.codecool.samu.codecoolinterview.dbTarget.repository.HeldExamRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeldExamService {
    private HeldExamRepository heldExamRepository;
    private ResultService resultService;
    public HeldExamService(HeldExamRepository heldExamRepository, ResultService resultService) {
        this.heldExamRepository = heldExamRepository;
        this.resultService = resultService;
    }

    public HeldExam addHeldExam(Exam exam, boolean success, List<ResultDto> results) {
        HeldExam heldExam = new HeldExam(
            exam,
            success
        );
        HeldExam newHeldExam = heldExamRepository.save(heldExam);
        for (ResultDto result: results) {
            resultService.addResult(heldExam, result);
        }
        return newHeldExam;
    }
}
