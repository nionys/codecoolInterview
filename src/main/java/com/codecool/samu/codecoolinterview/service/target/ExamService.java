package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.*;
import com.codecool.samu.codecoolinterview.dbTarget.repository.ExamRepository;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ExamDto;
import com.codecool.samu.codecoolinterview.dto.jacksonObject.ResultDto;
import com.codecool.samu.codecoolinterview.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final StudentService studentService;
    private final MentorService mentorService;
    private final HeldExamService heldExamService;
    private final ResultService resultService;
    public ExamService(
        ExamRepository examRepository,
        StudentService studentService,
        MentorService mentorService,
        HeldExamService heldExamService,
        ResultService resultService) {
        this.examRepository = examRepository;
        this.studentService = studentService;
        this.mentorService = mentorService;
        this.heldExamService = heldExamService;
        this.resultService = resultService;
    }

    public ExamDto saveExam(ExamDto examDto) {
        Exam newExam = examRepository.save(convertToExam(examDto));
        HeldExam heldExam = null;
        if (examDto.isHeld()) {
            heldExam = heldExamService.addHeldExam(newExam, examDto.getSuccess(), examDto.getResults());
        }
        return convertToExamDto(newExam, heldExam);
    }

    public Exam convertToExam(ExamDto examDto) {
        Student student = studentService.getStudentByEmail(examDto.getStudentEmail());
        Mentor mentor = mentorService.getMentorByEmail(examDto.getMentorEmail());
        return convertToExam(examDto, student, mentor);
    }

    public Exam convertToExam(ExamDto examDto, Student student, Mentor mentor) {
        String module = examDto.getModule();
        LocalDate date = DateUtil.convertToLocalDate(examDto.getDate());
        String comment = examDto.getComment();
        return new Exam(
            student,
            mentor,
            module,
            date,
            comment
        );
    }

    public ExamDto convertToExamDto(Exam exam, HeldExam heldExam) {
        String module = exam.getModule();
        String mentorEmail = exam.getMentor().getPerson().getEmail();
        String studentEmail = exam.getStudent().getPerson().getEmail();
        Date date = DateUtil.convertToDate(exam.getDate());
        String comment = exam.getComment();
        boolean cancelled = (heldExam == null);
        Boolean success = null;
        List<ResultDto> results = null;
        if (!cancelled) {
            success = heldExam.isSuccessful();
            results = resultService.findByHeldExamAsDto(heldExam);
        }
        return new ExamDto(
            module,
            mentorEmail,
            studentEmail,
            date,
            cancelled,
            comment,
            success,
            results
        );

    }

    public ExamDto convertToExamDto(Exam exam) {
        HeldExam heldExam = heldExamService.findByExamId(exam.getId()).orElse(null);
        return convertToExamDto(exam, heldExam);
    }
}
