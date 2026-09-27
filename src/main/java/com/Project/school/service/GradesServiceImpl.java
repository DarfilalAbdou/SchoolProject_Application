package com.Project.school.service;

import com.Project.school.dto.GradesDTO;
import com.Project.school.entity.Courses;
import com.Project.school.entity.Grades;
import com.Project.school.entity.Student;
import com.Project.school.repository.CoursesRepository;
import com.Project.school.repository.GradesRepository;
import com.Project.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradesServiceImpl implements GradesService {

    private static final Logger logger = LoggerFactory.getLogger(GradesServiceImpl.class);

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public List<GradesDTO> getAllGrades() {
        return gradesRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradesDTO getGradeById(Long id) {
        Grades grade = gradesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Grade not found with id; " + id);
                    return new RuntimeException("Grade not found with id: " + id);
                });
        return toDTO(grade);
    }

    @Override
    public List<GradesDTO> getGradesByStudentId(Long studentId) {
        return gradesRepository.findByStudentId(studentId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GradesDTO> getGradesByCourseId(Long courseId) {
        return gradesRepository.findByCourseId(courseId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradesDTO createGrade(GradesDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> {
                    logger.error("Create : No Studnet with id: " +  dto.getStudentId());
                    return new RuntimeException("Create: No Student with id: " + dto.getStudentId());
                });
        Courses course = coursesRepository.findById(dto.getCourseId())
                .orElseThrow(() -> {
                    logger.error("Create : No Course with id : "+  dto.getCourseId());
                    return new RuntimeException(" Create : No Course with id: " + dto.getCourseId());
                });

        Grades grade = new Grades();
        grade.setGrade(dto.getGrade());
        grade.setStudent(student);
        grade.setCourse(course);

        Grades saved = gradesRepository.save(grade);
        logger.info("Created grade id=" + saved.getId() + "for studentId=" +student.getId() + " courseId=" + course.getId());
        return toDTO(saved);
    }

    @Override
    public GradesDTO updateGrade(Long id, GradesDTO dto) {
        Grades grade = gradesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Update : No Studnet with id: " +  dto.getStudentId());
                    return new RuntimeException("Update : No Student with id: " + dto.getStudentId());
                });
        grade.setGrade(dto.getGrade());

        Grades updated = gradesRepository.save(grade);
        logger.info("Updated grade id:" +  updated.getId());
        return toDTO(updated);
    }

    @Override
    public void deleteGrade(Long id) {
        if (!gradesRepository.existsById(id)) {
            logger.warn("Delete : No grade with this id:"+  id);
            throw new RuntimeException("Delete : No grade with this id: " + id);
        }
        gradesRepository.deleteById(id);
        logger.info("Deleted grade id=" + id);
    }

    private GradesDTO toDTO(Grades grade) {
        return new GradesDTO(
                grade.getId(),
                grade.getGrade(),
                grade.getStudent().getId(),
                grade.getCourse().getId()
        );
    }
}