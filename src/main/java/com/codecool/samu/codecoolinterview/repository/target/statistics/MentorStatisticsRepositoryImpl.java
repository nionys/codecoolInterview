package com.codecool.samu.codecoolinterview.repository.target.statistics;

import com.codecool.samu.codecoolinterview.model.dto.queryObject.MentorQueryRecord;
import com.codecool.samu.codecoolinterview.model.entity.target.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MentorStatisticsRepositoryImpl implements MentorStatisticsRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MentorQueryRecord> calculateMentorPassRatePerAttempt() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MentorQueryRecord> query = cb.createQuery(MentorQueryRecord.class);

        Root<HeldExam> heldExamRoot = query.from(HeldExam.class);
        Join<HeldExam, Exam> examJoin = heldExamRoot.join("exam", JoinType.LEFT);
        Join<Exam, Mentor> mentorJoin = examJoin.join("mentor", JoinType.LEFT);
        Join<Mentor, Person> mentorPersonJoin = mentorJoin.join("person", JoinType.LEFT);
        Join<Exam, Student> studentJoin = examJoin.join("student", JoinType.LEFT);


        Subquery<Long> subquery = buildDenseRankSubquery(cb, query, studentJoin, examJoin);

        query.groupBy(mentorPersonJoin.get("email"), subquery);
        query.multiselect(
            mentorPersonJoin.get("email"),
            subquery,
            cb.quot(
                cb.sum(heldExamRoot.get("successful").as(Integer.class)).as(Double.class),
                cb.count(heldExamRoot.get("id")))
        );
        TypedQuery<MentorQueryRecord> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    private Subquery<Long> buildDenseRankSubquery(
        CriteriaBuilder cb,
        CriteriaQuery<?> query,
        Path<?> outerStudentPath,
        Path<?> outerExamPath) {
        Subquery<Long> subquery = query.subquery(Long.class);

        Root<HeldExam> innerHeldExamRoot = subquery.from(HeldExam.class);
        Join<HeldExam, Exam> innerExamJoin = innerHeldExamRoot.join("exam");
        Join<Exam, Student> innerStudentJoin = innerExamJoin.join("student");

        subquery.where(cb.and(
            cb.equal(innerStudentJoin.get("id"), outerStudentPath.get("id")),
            cb.equal(innerExamJoin.get("module"), outerExamPath.get("module")),
            cb.greaterThan(outerExamPath.get("date"), innerExamJoin.get("date"))
        ));
        subquery.select(cb.sum(1, cb.count(innerHeldExamRoot.get("id"))).as(Long.class));
        return subquery;
    }
}
