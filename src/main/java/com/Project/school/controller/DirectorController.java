package com.Project.school.controller;

import com.Project.school.dto.DirectorDTO;
import com.Project.school.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<DirectorDTO> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO dto) {
        return ResponseEntity.ok(directorService.createDirector(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorDTO dto) {
        return ResponseEntity.ok(directorService.updateDirector(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
