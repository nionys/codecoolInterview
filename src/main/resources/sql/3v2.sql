SELECT result.dimension, AVG(result.percentage), count(*)
FROM result
         LEFT JOIN held_exam on result.heldexam_id = held_exam.id
         LEFT JOIN exam AS outerexam on held_exam.exam_id = outerexam.id
WHERE outerexam.date = (SELECT MAX(innerexam.date) as lastdate
                        FROM held_exam
                                LEFT JOIN exam AS innerexam on held_exam.exam_id = innerexam.id
                                LEFT JOIN public.student student on innerexam.student_id = student.id
                                LEFT JOIN public.person person on student.person_id = person.id
                        WHERE person.email = 'foo@bar.com'
                                AND outerexam.module = innerexam.module
                        GROUP BY innerexam.module)
GROUP BY result.dimension;