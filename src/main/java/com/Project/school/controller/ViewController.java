package com.Project.school.controller;

import com.Project.school.dto.CoursesDTO;
import com.Project.school.dto.DirectorDTO;
import com.Project.school.dto.GradesDTO;
import com.Project.school.dto.StudentDTO;
import com.Project.school.service.CoursesService;
import com.Project.school.service.DirectorService;
import com.Project.school.service.GradesService;
import com.Project.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    @Autowired
    private GradesService gradesService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam Long userId,
                               @RequestParam String password,
                               @RequestParam String role,
                               RedirectAttributes redirectAttributes) {
        try {
            if ("teacher".equals(role)) {
                DirectorDTO director = directorService.getDirectorById(userId);
                if (director.getPassword() != null && director.getPassword().equals(password)) {
                    return "redirect:/director/" + userId + "/space";
                }
            } else {
                StudentDTO student = studentService.getStudentById(userId);
                if (student.getPassword() != null && student.getPassword().equals(password)) {
                    return "redirect:/student/" + userId + "/space";
                }
            }
            redirectAttributes.addFlashAttribute("error", "Identifiant ou mot de passe incorrect.");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Utilisateur non trouvé.");
            return "redirect:/";
        }
    }

    @GetMapping("/student/{id}/space")
    public String studentSpace(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getStudentById(id);
        List<GradesDTO> grades = gradesService.getGradesByStudentId(id);

        model.addAttribute("student", student);
        model.addAttribute("grades", grades);

        return "student_space";
    }

    @GetMapping("/director/{id}/space")
    public String directorSpace(@PathVariable Long id, Model model) {
        DirectorDTO director = directorService.getDirectorById(id);
        List<CoursesDTO> courses = coursesService.getCoursesByDirectorId(id);

        // For each course, get students and their grades
        Map<Long, List<GradesDTO>> courseGrades = new HashMap<>();
        for (CoursesDTO course : courses) {
            courseGrades.put(course.getId(), gradesService.getGradesByCourseId(course.getId()));
        }

        model.addAttribute("director", director);
        model.addAttribute("courses", courses);
        model.addAttribute("courseGrades", courseGrades);
        model.addAttribute("allStudents", studentService.getAllStudents());

        return "director_space";
    }

    @PostMapping("/director/{directorId}/grade/add")
    public String addGrade(@PathVariable Long directorId,
                           @RequestParam Long studentId,
                           @RequestParam Long courseId,
                           @RequestParam String grade,
                           RedirectAttributes redirectAttributes) {
        try {
            GradesDTO dto = new GradesDTO();
            dto.setStudentId(studentId);
            dto.setCourseId(courseId);
            dto.setGrade(grade);
            gradesService.createGrade(dto);
            redirectAttributes.addFlashAttribute("success", "Note ajoutée avec succès.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout de la note : " + e.getMessage());
        }
        return "redirect:/director/" + directorId + "/space";
    }

    @PostMapping("/director/{directorId}/grade/update")
    public String updateGrade(@PathVariable Long directorId,
                              @RequestParam Long gradeId,
                              @RequestParam String grade,
                              RedirectAttributes redirectAttributes) {
        try {
            GradesDTO dto = new GradesDTO();
            dto.setGrade(grade);
            gradesService.updateGrade(gradeId, dto);
            redirectAttributes.addFlashAttribute("success", "Note mise à jour avec succès.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour : " + e.getMessage());
        }
        return "redirect:/director/" + directorId + "/space";
    }

    @PostMapping("/director/{directorId}/grade/delete")
    public String deleteGrade(@PathVariable Long directorId,
                              @RequestParam Long gradeId,
                              RedirectAttributes redirectAttributes) {
        try {
            gradesService.deleteGrade(gradeId);
            redirectAttributes.addFlashAttribute("success", "Note supprimée avec succès.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/director/" + directorId + "/space";
    }

    @PostMapping("/director/{directorId}/course/add")
    public String addCourse(@PathVariable Long directorId,
                            @RequestParam String courseName,
                            RedirectAttributes redirectAttributes) {
        try {
            CoursesDTO dto = new CoursesDTO();
            dto.setName(courseName);
            dto.setDirectorId(directorId);
            coursesService.createCourse(dto);
            redirectAttributes.addFlashAttribute("success", "Cours ajouté avec succès.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'ajout du cours : " + e.getMessage());
        }
        return "redirect:/director/" + directorId + "/space";
    }

    @PostMapping("/director/{directorId}/course/delete")
    public String deleteCourse(@PathVariable Long directorId,
                               @RequestParam Long courseId,
                               RedirectAttributes redirectAttributes) {
        try {
            coursesService.deleteCourse(courseId);
            redirectAttributes.addFlashAttribute("success", "Cours supprimé avec succès.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression du cours : " + e.getMessage());
        }
        return "redirect:/director/" + directorId + "/space";
    }
}
