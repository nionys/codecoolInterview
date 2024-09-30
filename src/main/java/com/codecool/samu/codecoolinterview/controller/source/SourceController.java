package com.codecool.samu.codecoolinterview.controller.source;

import com.codecool.samu.codecoolinterview.model.entity.source.JsonRecord;
import com.codecool.samu.codecoolinterview.model.dto.NewJsonRecordDto;
import com.codecool.samu.codecoolinterview.service.source.SourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourceController {
    private SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/record/all")
    public List<JsonRecord> getAllRecords() {
        return sourceService.getAllRecords();
    }

    @GetMapping("/record/{id}")
    public JsonRecord getRecordById(@PathVariable int id) {
        return sourceService.getRecordById(id);
    }

    @PostMapping("/record")
    public NewJsonRecordDto addRecord(@RequestBody String json) {
        return sourceService.addRecord(json);

    }

}
