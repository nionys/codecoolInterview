package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.HeldExam;
import com.codecool.samu.codecoolinterview.dbTarget.model.Result;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ResultRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void addResult(HeldExam heldExam, ResultDto result) {
        Result newResult = new Result(
            heldExam,
            result.getDimension(),
            result.getPercentage()
        );
        resultRepository.save(newResult);
    }

    public List<ResultDto> findByHeldExamAsDto(HeldExam heldExam) {
        return resultRepository.findByHeldExam(heldExam)
            .stream().map(this::convertToResultDto)
            .toList();
    }

    public ResultDto convertToResultDto(Result result) {
        return new ResultDto(
            result.getDimension(), result.getPercentage()
        );
    }
}
