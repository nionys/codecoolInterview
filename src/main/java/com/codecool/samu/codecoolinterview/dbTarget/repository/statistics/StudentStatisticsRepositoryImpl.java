package com.codecool.samu.codecoolinterview.dbTarget.repository.statistics;

import com.codecool.samu.codecoolinterview.dbTarget.model.*;
import com.codecool.samu.codecoolinterview.dto.queryObject.ResultQueryObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StudentStatisticsRepositoryImpl implements StudentStatisticsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ResultQueryObject> calculateAverageResultsByStudent(String studentEmail) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResultQueryObject> query = cb.createQuery(ResultQueryObject.class);

        Root<Result> resultRoot = query.from(Result.class);
        Join<Result, HeldExam> heldExamJoin = resultRoot.join("heldExam", JoinType.LEFT);
        Join<HeldExam, Exam> examJoin = heldExamJoin.join("exam", JoinType.LEFT);

        Subquery<Date> lastDateSubquery = buildLastDateSubquery(studentEmail, cb, query, examJoin);

        query.where(cb.equal(examJoin.get("date"), lastDateSubquery));
        query.groupBy(resultRoot.get("dimension"));
        query.multiselect(
            resultRoot.get("dimension"),
            cb.avg(resultRoot.get("percentage"))
        );

        TypedQuery<ResultQueryObject> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private Subquery<Date> buildLastDateSubquery(
        String studentEmail,
        CriteriaBuilder cb,
        CriteriaQuery<ResultQueryObject> query, Join<HeldExam,
        Exam> outerExamJoin) {
        Subquery<Date> subquery = query.subquery(Date.class);
        Root<HeldExam> innerDateRoot = subquery.from(HeldExam.class);
        Join<HeldExam, Exam> innerExamJoin = innerDateRoot.join("exam", JoinType.LEFT);
        Join<Exam, Student> innerStudentJoin = innerExamJoin.join("student", JoinType.LEFT);
        Join<Student, Person> innerPersonJoin = innerStudentJoin.join("person", JoinType.LEFT);
        subquery.where(cb.and(
            cb.equal(innerPersonJoin.get("email"), studentEmail),
            cb.equal(outerExamJoin.get("module"), innerExamJoin.get("module"))
        ));
        subquery.groupBy(innerExamJoin.get("module"));
        subquery.select(cb.greatest(innerExamJoin.<Date>get("date")));
        return subquery;
    }
}
