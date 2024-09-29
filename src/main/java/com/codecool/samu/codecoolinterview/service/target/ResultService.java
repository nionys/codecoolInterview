package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.HeldExam;
import com.codecool.samu.codecoolinterview.dbTarget.model.Result;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ResultRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    private ResultRepository resultRepository;
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Result addResult(HeldExam heldExam, ResultDto result) {
        Result newResult = new Result(
            heldExam,
            result.getDimension(),
            result.getPercentage()
        );
        return resultRepository.save(newResult);
    }
}
