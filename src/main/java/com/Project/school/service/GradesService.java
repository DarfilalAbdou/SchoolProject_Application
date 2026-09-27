package com.Project.school.service;

import com.Project.school.dto.GradesDTO;

import java.util.List;

public interface GradesService {

    List<GradesDTO> getAllGrades();

    GradesDTO getGradeById(Long id);

    List<GradesDTO> getGradesByStudentId(Long studentId);

    List<GradesDTO> getGradesByCourseId(Long courseId);

    GradesDTO createGrade(GradesDTO dto);

    GradesDTO updateGrade(Long id, GradesDTO dto);

    void deleteGrade(Long id);
}