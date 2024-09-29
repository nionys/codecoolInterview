package com.codecool.samu.codecoolinterview.service.target;

import com.codecool.samu.codecoolinterview.dbTarget.model.*;
import com.codecool.samu.codecoolinterview.dto.QueryResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAverageService {
    @Autowired
    EntityManager entityManager;

    public List<QueryResult> averageOfStudentPerDimension(String studentEmail) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QueryResult> query = cb.createQuery(QueryResult.class);

        Root<Result> resultTable = query.from(Result.class);
        Join<Result, HeldExam> heldExamJoin = resultTable.join("heldExam", JoinType.LEFT);
        Join<HeldExam, Exam> examJoin = heldExamJoin.join("exam", JoinType.LEFT);
        Join<Exam, Student> studentJoin = examJoin.join("student", JoinType.LEFT);
        Join<Student, Person> studentPersonJoin = studentJoin.join("person", JoinType.LEFT);
        Join<Exam, Mentor> mentorJoin = examJoin.join("mentor", JoinType.LEFT);
        Join<Student, Person> mentorPersonJoin = mentorJoin.join("person", JoinType.LEFT);

        query.multiselect(
            resultTable.get("dimension"),
            cb.avg(resultTable.get("percentage"))
        );

        query.where(cb.equal(studentPersonJoin.get("email"), studentEmail));
        query.orderBy(cb.desc(examJoin.get("date")));
        query.groupBy(resultTable.get("dimension"));

        TypedQuery<QueryResult> queryResult = entityManager.createQuery(query);
        List<QueryResult> results = queryResult.getResultList();
        return results;
    }


}
