package com.Project.school.service;

import com.Project.school.dto.CoursesDTO;

import java.util.List;

public interface CoursesService {

    List<CoursesDTO> getAllCourses();

    CoursesDTO getCourseById(Long id);

    List<CoursesDTO> getCoursesByDirectorId(Long directorId);

    CoursesDTO createCourse(CoursesDTO dto);

    CoursesDTO updateCourse(Long id, CoursesDTO dto);

    void deleteCourse(Long id);
}