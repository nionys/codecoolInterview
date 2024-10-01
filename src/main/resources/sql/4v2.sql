SELECT
    email,
    (SELECT 1+COUNT(*)
     FROM held_exam he2
        LEFT JOIN exam e2 ON he2.exam_id = e2.id
        LEFT JOIN student s2 on e2.student_id = s2.id
     WHERE s.id = s2.id
        AND e.module = e2.module
        AND e.date > e2.date
     ) examnumber,
    SUM(successful::int)::decimal/COUNT(*) as passrate
FROM held_exam
    LEFT JOIN exam e on held_exam.exam_id = e.id
    LEFT JOIN mentor m  on e.mentor_id = m.id
    LEFT JOIN person p on m.person_id = p.id
    LEFT JOIN student s on e.student_id = s.id
group by email, examnumber;

