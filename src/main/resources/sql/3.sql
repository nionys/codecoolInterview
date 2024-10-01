WITH helper AS (SELECT exam.module, MAX(exam.date) as lastdate
                FROM held_exam
                         LEFT JOIN exam on held_exam.exam_id = exam.id
                         LEFT JOIN public.student student on exam.student_id = student.id
                         LEFT JOIN public.person person on student.person_id = person.id
                WHERE person.email = 'foo@bar.com'
                GROUP BY exam.module)
SELECT result.dimension, AVG(result.percentage), count(*)
FROM result
         LEFT JOIN held_exam on result.heldexam_id = held_exam.id
         LEFT JOIN exam on held_exam.exam_id = exam.id
         RIGHT JOIN helper on exam.module = helper.module
WHERE exam.date = helper.lastdate
GROUP BY result.dimension;





