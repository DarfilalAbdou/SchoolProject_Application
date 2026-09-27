DELETE FROM grades;
DELETE FROM courses;
DELETE FROM students;
DELETE FROM directors;
--Taken from AI i'm not gonna lie
-- Directors
INSERT INTO directors (last_name, email) VALUES ('Smith', 'smith@school.com');
INSERT INTO directors (last_name, email) VALUES ('Johnson', 'johnson@school.com');
INSERT INTO directors (last_name, email) VALUES ('Williams', 'williams@school.com');

-- Students
INSERT INTO students (first_name, last_name, email) VALUES ('Alice', 'Brown', 'alice.brown@school.com');
INSERT INTO students (first_name, last_name, email) VALUES ('Bob', 'Davis', 'bob.davis@school.com');
INSERT INTO students (first_name, last_name, email) VALUES ('Charlie', 'Miller', 'charlie.miller@school.com');
INSERT INTO students (first_name, last_name, email) VALUES ('Diana', 'Wilson', 'diana.wilson@school.com');
INSERT INTO students (first_name, last_name, email) VALUES ('Ethan', 'Moore', 'ethan.moore@school.com');

-- Courses (director_id references the directors inserted above: 1=Smith, 2=Johnson, 3=Williams)
INSERT INTO courses (name, director_id) VALUES ('Mathematics 101', 1);
INSERT INTO courses (name, director_id) VALUES ('Physics 201', 1);
INSERT INTO courses (name, director_id) VALUES ('Computer Science 301', 2);
INSERT INTO courses (name, director_id) VALUES ('History 101', 3);
INSERT INTO courses (name, director_id) VALUES ('English Literature', 3);

-- Grades (student_id and course_id reference the rows above)
INSERT INTO grades (grade, student_id, course_id) VALUES ('A', 1, 1);
INSERT INTO grades (grade, student_id, course_id) VALUES ('B+', 1, 2);
INSERT INTO grades (grade, student_id, course_id) VALUES ('A-', 2, 1);
INSERT INTO grades (grade, student_id, course_id) VALUES ('C', 2, 3);
INSERT INTO grades (grade, student_id, course_id) VALUES ('B', 3, 3);
INSERT INTO grades (grade, student_id, course_id) VALUES ('A', 3, 4);
INSERT INTO grades (grade, student_id, course_id) VALUES ('B-', 4, 4);
INSERT INTO grades (grade, student_id, course_id) VALUES ('A+', 4, 5);
INSERT INTO grades (grade, student_id, course_id) VALUES ('C+', 5, 2);
INSERT INTO grades (grade, student_id, course_id) VALUES ('B', 5, 5);