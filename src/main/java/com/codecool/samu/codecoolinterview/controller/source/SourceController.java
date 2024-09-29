package com.codecool.samu.codecoolinterview.controller.source;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
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

    @GetMapping("/record/all/skip/{skip}")
    public List<JsonRecord> getAllRecordsWithSkip(@PathVariable int skip) {
        return sourceService.getAllRecordsWithSkip(skip);
    }


    @GetMapping("/record/{id}")
    public JsonRecord getRecordById(@PathVariable int id) {
        return sourceService.getRecordById(id);
    }

    @PostMapping("/record")
    public long addRecord(@RequestBody String json) {
        return sourceService.addRecord(new JsonRecord(json));
    }

}
