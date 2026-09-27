package com.Project.school.repository;

import com.Project.school.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    List<Courses> findByDirectorId(Long directorId);

    Optional<Courses> findByName(String name);
}