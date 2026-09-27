package com.Project.school.service;

import com.Project.school.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createStudent(StudentDTO dto);

    StudentDTO updateStudent(Long id, StudentDTO dto);

    void deleteStudent(Long id);
}