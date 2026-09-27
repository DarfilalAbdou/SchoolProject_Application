package com.Project.school.controller;

import com.Project.school.dto.GradesDTO;
import com.Project.school.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradesController {

    @Autowired
    private GradesService gradesService;

    @GetMapping
    public List<GradesDTO> getAllGrades() {
        return gradesService.getAllGrades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradesDTO> getGradeById(@PathVariable Long id) {
        return ResponseEntity.ok(gradesService.getGradeById(id));
    }

    @GetMapping("/student/{studentId}")
    public List<GradesDTO> getGradesByStudentId(@PathVariable Long studentId) {
        return gradesService.getGradesByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<GradesDTO> getGradesByCourseId(@PathVariable Long courseId) {
        return gradesService.getGradesByCourseId(courseId);
    }

    @PostMapping
    public ResponseEntity<GradesDTO> createGrade(@RequestBody GradesDTO dto) {
        return ResponseEntity.ok(gradesService.createGrade(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradesDTO> updateGrade(@PathVariable Long id, @RequestBody GradesDTO dto) {
        return ResponseEntity.ok(gradesService.updateGrade(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradesService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
