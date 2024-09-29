package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.*;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ExamRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.HeldExamRepository;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ResultRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ExamDto;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ExamService {
    private ExamRepository examRepository;
    private HeldExamRepository heldExamRepository;
    private ResultRepository resultRepository;
    private StudentService studentService;
    private MentorService mentorService;
    private HeldExamService heldExamService;
    private ResultService resultService;
    public ExamService(
        ExamRepository examRepository,
        HeldExamRepository heldExamRepository,
        ResultRepository resultRepository,
        StudentService studentService,
        MentorService mentorService,
        HeldExamService heldExamService,
        ResultService resultService) {
        this.examRepository = examRepository;
        this.heldExamRepository = heldExamRepository;
        this.resultRepository = resultRepository;
        this.studentService = studentService;
        this.mentorService = mentorService;
        this.heldExamService = heldExamService;
        this.resultService = resultService;
    }

    public Exam saveExam(ExamDto exam) {
        Student student = studentService.getStudentByEmail(exam.getStudentEmail());
        Mentor mentor = mentorService.getMentorByEmail(exam.getMentorEmail());
        String module = exam.getModule();
        LocalDate date = exam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String comment = exam.getComment();
        Exam newExam = new Exam(
            student,
            mentor,
            module,
            date,
            comment
        );
        examRepository.save(newExam);
        if (exam.isHeld()) {
            heldExamService.addHeldExam(newExam, exam.getSuccess(), exam.getResults());
        }
        return newExam;
    }
}
