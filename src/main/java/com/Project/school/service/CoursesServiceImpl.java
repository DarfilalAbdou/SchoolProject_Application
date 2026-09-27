package com.Project.school.service;

import com.Project.school.dto.CoursesDTO;
import com.Project.school.entity.Courses;
import com.Project.school.entity.Director;
import com.Project.school.repository.CoursesRepository;
import com.Project.school.repository.DirectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImpl implements CoursesService {

    private static final Logger logger = LoggerFactory.getLogger(CoursesServiceImpl.class);

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<CoursesDTO> getAllCourses() {
        return coursesRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CoursesDTO getCourseById(Long id) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Course not found with id; " + id);
                    return new RuntimeException("Course not found with id: " + id);
                });
        return toDTO(course);
    }

    @Override
    public List<CoursesDTO> getCoursesByDirectorId(Long directorId) {
        return coursesRepository.findByDirectorId(directorId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CoursesDTO createCourse(CoursesDTO dto) {
        Courses course = new Courses();
        course.setName(dto.getName());

        if (dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> {
                        logger.error("Create : No Director with id: " + dto.getDirectorId());
                        return new RuntimeException("Create: No Director with id: " + dto.getDirectorId());
                    });
            course.setDirector(director);
        }

        Courses saved = coursesRepository.save(course);
        logger.info("Created course id=" + saved.getId() + " name=" + saved.getName());
        return toDTO(saved);
    }

    @Override
    public CoursesDTO updateCourse(Long id, CoursesDTO dto) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Update : No Course with id: " + id);
                    return new RuntimeException("Update : No Course with id: " + id);
                });
        course.setName(dto.getName());

        Courses updated = coursesRepository.save(course);
        logger.info("Updated course id:" + updated.getId());
        return toDTO(updated);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!coursesRepository.existsById(id)) {
            logger.warn("Delete : No course with this id:" + id);
            throw new RuntimeException("Delete : No course with this id: " + id);
        }
        coursesRepository.deleteById(id);
        logger.info("Deleted course id=" + id);
    }

    private CoursesDTO toDTO(Courses course) {
        Long directorId = null;
        if (course.getDirector() != null) {
            directorId = course.getDirector().getId();
        }
        return new CoursesDTO(course.getId(), course.getName(), directorId);
    }
}