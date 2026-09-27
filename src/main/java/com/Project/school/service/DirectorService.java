package com.Project.school.service;

import com.Project.school.dto.DirectorDTO;

import java.util.List;

public interface DirectorService {

    List<DirectorDTO> getAllDirectors();

    DirectorDTO getDirectorById(Long id);

    DirectorDTO createDirector(DirectorDTO dto);

    DirectorDTO updateDirector(Long id, DirectorDTO dto);

    void deleteDirector(Long id);
}