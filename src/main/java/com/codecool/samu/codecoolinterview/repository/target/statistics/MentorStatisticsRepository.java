package com.codecool.samu.codecoolinterview.repository.target.statistics;

import com.codecool.samu.codecoolinterview.model.entity.target.Mentor;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Map;

public interface MentorStatisticsRepository extends Repository<Mentor, Long> {
    @Query(value = """
        SELECT email, examnumber, CONCAT(ROUND(100*SUM(successful::int)::decimal/COUNT(*),0), '%') as passrate
        FROM (
            SELECT
                person.email,
                DENSE_RANK () OVER (
                    PARTITION BY exam.module
                    ORDER BY exam.date
                    ) AS examnumber,
                held_exam.successful
            FROM held_exam
                LEFT JOIN exam on held_exam.exam_id = exam.id
                LEFT JOIN mentor on exam.mentor_id = mentor.id
                LEFT JOIN person on mentor.person_id = person.id) as indexedexams
        GROUP BY email, examnumber;
        """,
        nativeQuery = true)
    List<Map<Tuple, Double>> calculateMentorPassRatePerAttempt();

}
