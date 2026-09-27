--Created by AI
-- Clear child tables first, then parent tables
DELETE FROM grades;
DELETE FROM courses;
DELETE FROM students;
DELETE FROM directors;

-- Directors (Explicit IDs guarantee matching foreign keys)
INSERT INTO directors (id, last_name, email, password) VALUES (1, 'Smith', 'smith@school.com', 'admin123');
INSERT INTO directors (id, last_name, email, password) VALUES (2, 'Johnson', 'johnson@school.com', 'admin123');
INSERT INTO directors (id, last_name, email, password) VALUES (3, 'Williams', 'williams@school.com', 'admin123');

-- Students
INSERT INTO students (id, first_name, last_name, email, password) VALUES (1, 'Alice', 'Brown', 'alice.brown@school.com', 'pass123');
INSERT INTO students (id, first_name, last_name, email, password) VALUES (2, 'Bob', 'Davis', 'bob.davis@school.com', 'pass123');
INSERT INTO students (id, first_name, last_name, email, password) VALUES (3, 'Charlie', 'Miller', 'charlie.miller@school.com', 'pass123');
INSERT INTO students (id, first_name, last_name, email, password) VALUES (4, 'Diana', 'Wilson', 'diana.wilson@school.com', 'pass123');
INSERT INTO students (id, first_name, last_name, email, password) VALUES (5, 'Ethan', 'Moore', 'ethan.moore@school.com', 'pass123');

-- Courses
INSERT INTO courses (id, name, director_id) VALUES (1, 'Mathematics 101', 1);
INSERT INTO courses (id, name, director_id) VALUES (2, 'Physics 201', 1);
INSERT INTO courses (id, name, director_id) VALUES (3, 'Computer Science 301', 2);
INSERT INTO courses (id, name, director_id) VALUES (4, 'History 101', 3);
INSERT INTO courses (id, name, director_id) VALUES (5, 'English Literature', 3);

-- Grades
INSERT INTO grades (id, grade, student_id, course_id) VALUES (1, 'A', 1, 1);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (2, 'B+', 1, 2);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (3, 'A-', 2, 1);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (4, 'C', 2, 3);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (5, 'B', 3, 3);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (6, 'A', 3, 4);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (7, 'B-', 4, 4);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (8, 'A+', 4, 5);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (9, 'C+', 5, 2);
INSERT INTO grades (id, grade, student_id, course_id) VALUES (10, 'B', 5, 5);

-- Synchronize sequences for H2 Identity columns
ALTER TABLE directors ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM directors);
ALTER TABLE students ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM students);
ALTER TABLE courses ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM courses);
ALTER TABLE grades ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM grades);
