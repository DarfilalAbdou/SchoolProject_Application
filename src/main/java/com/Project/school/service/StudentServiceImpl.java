package com.Project.school.service;

import com.Project.school.dto.StudentDTO;
import com.Project.school.entity.Student;
import com.Project.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with id; " + id);
                    return new RuntimeException("Student not found with id: " + id);
                });
        return toDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());

        Student saved = studentRepository.save(student);
        logger.info("Created student id=" + saved.getId() + " lastname=" + saved.getLastname());
        return toDTO(saved);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Update : No Student with id: " + id);
                    return new RuntimeException("Update : No Student with id: " + id);
                });
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());

        Student updated = studentRepository.save(student);
        logger.info("Updated student id:" + updated.getId());
        return toDTO(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            logger.warn("Delete : No student with this id:" + id);
            throw new RuntimeException("Delete : No student with this id: " + id);
        }
        studentRepository.deleteById(id);
        logger.info("Deleted student id=" + id);
    }

    private StudentDTO toDTO(Student student) {
        return new StudentDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getEmail(), student.getPassword());
    }
}
