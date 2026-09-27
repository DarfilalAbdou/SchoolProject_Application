package com.Project.school.repository;

import com.Project.school.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {
    List<Grades> findByStudentId(Long studentId);

    List<Grades> findByCourseId(Long courseId);

    Optional<Grades> findByStudentIdAndCourseId(Long studentId, Long courseId);
}