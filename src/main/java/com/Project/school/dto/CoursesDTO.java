package com.Project.school.dto;

public class CoursesDTO {

    private Long id;
    private String name;
    private Long directorId;

    public CoursesDTO() {
    }

    public CoursesDTO(Long id, String name, Long directorId) {
        this.id = id;
        this.name = name;
        this.directorId = directorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}