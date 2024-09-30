SELECT email, examnumber, SUM(successful::int)::decimal/COUNT(*) as passrate
FROM (
    SELECT
        person.email,
        DENSE_RANK () OVER (
            PARTITION BY exam.module
            ORDER BY exam.date
            ) examnumber,
        held_exam.successful
    FROM held_exam
        LEFT JOIN exam on held_exam.exam_id = exam.id
        LEFT JOIN mentor on exam.mentor_id = mentor.id
        LEFT JOIN person on mentor.person_id = person.id) as indexedexams
GROUP BY email, examnumber;

