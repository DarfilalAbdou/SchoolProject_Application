package com.Project.school.dto;

public class GradesDTO {

    private Long id;
    private String grade;
    private Long studentId;
    private Long courseId;
    private String courseName;

    public GradesDTO() {
    }

    public GradesDTO(Long id, String grade, Long studentId, Long courseId, String courseName) {
        this.id = id;
        this.grade = grade;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
