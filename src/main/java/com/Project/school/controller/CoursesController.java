package com.Project.school.controller;

import com.Project.school.dto.CoursesDTO;
import com.Project.school.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping
    public List<CoursesDTO> getAllCourses() {
        return coursesService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursesDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(coursesService.getCourseById(id));
    }

    @GetMapping("/director/{directorId}")
    public List<CoursesDTO> getCoursesByDirectorId(@PathVariable Long directorId) {
        return coursesService.getCoursesByDirectorId(directorId);
    }

    @PostMapping
    public ResponseEntity<CoursesDTO> createCourse(@RequestBody CoursesDTO dto) {
        return ResponseEntity.ok(coursesService.createCourse(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursesDTO> updateCourse(@PathVariable Long id, @RequestBody CoursesDTO dto) {
        return ResponseEntity.ok(coursesService.updateCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
