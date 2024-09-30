package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.repository.target.ExamRepository;
import com.codecool.samu.codecoolinterview.model.dto.ExamDto;
import com.codecool.samu.codecoolinterview.model.dto.MentorDto;
import com.codecool.samu.codecoolinterview.model.dto.StudentDto;
import com.codecool.samu.codecoolinterview.model.dto.jacksonObject.ParsedExamDto;
import com.codecool.samu.codecoolinterview.model.dto.jacksonObject.ResultDto;
import com.codecool.samu.codecoolinterview.model.entity.target.Exam;
import com.codecool.samu.codecoolinterview.model.entity.target.HeldExam;
import com.codecool.samu.codecoolinterview.model.entity.target.Mentor;
import com.codecool.samu.codecoolinterview.model.entity.target.Student;
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

    public void deleteAll() {
        examRepository.deleteAll();
    }

    public ExamDto saveExam(ParsedExamDto parsedExamDto) {
        Exam newExam = examRepository.save(convertToExam(parsedExamDto));
        HeldExam heldExam = null;
        if (parsedExamDto.isHeld()) {
            heldExam = heldExamService.addHeldExam(newExam, parsedExamDto.getSuccess(), parsedExamDto.getResults());
        }
        return convertToExamDto(newExam, heldExam);
    }

    public Exam convertToExam(ParsedExamDto parsedExamDto) {
        Student student = studentService.getStudentByEmail(parsedExamDto.getStudentEmail());
        Mentor mentor = mentorService.getMentorByEmail(parsedExamDto.getMentorEmail());
        return convertToExam(parsedExamDto, student, mentor);
    }

    public Exam convertToExam(ParsedExamDto parsedExamDto, Student student, Mentor mentor) {
        String module = parsedExamDto.getModule();
        LocalDate date = DateUtil.convertToLocalDate(parsedExamDto.getDate());
        String comment = parsedExamDto.getComment();
        return new Exam(
            student,
            mentor,
            module,
            date,
            comment
        );
    }

    public ParsedExamDto convertToParsedExamDto(Exam exam, HeldExam heldExam) {
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
        return new ParsedExamDto(
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

    public ExamDto convertToExamDto(Exam exam, HeldExam heldExam) {
        String module = exam.getModule();
        MentorDto mentor = mentorService.convertToMentorDto(exam.getMentor());
        StudentDto student = studentService.convertToStudentDto(exam.getStudent());
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
            mentor,
            student,
            date,
            cancelled,
            comment,
            success,
            results
        );

    }

    public ParsedExamDto convertToParsedExamDto(Exam exam) {
        HeldExam heldExam = heldExamService.findByExamId(exam.getId()).orElse(null);
        return convertToParsedExamDto(exam, heldExam);
    }
}
