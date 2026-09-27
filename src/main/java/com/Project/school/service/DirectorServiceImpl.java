package com.Project.school.service;

import com.Project.school.dto.DirectorDTO;
import com.Project.school.entity.Director;
import com.Project.school.repository.DirectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Director not found with id; " + id);
                    return new RuntimeException("Director not found with id: " + id);
                });
        return toDTO(director);
    }

    @Override
    public DirectorDTO createDirector(DirectorDTO dto) {
        Director director = new Director(dto.getLastName(), dto.getEmail(), dto.getPassword());
        Director saved = directorRepository.save(director);
        logger.info("Created director id=" + saved.getId() + " lastName=" + saved.getName());
        return toDTO(saved);
    }

    @Override
    public DirectorDTO updateDirector(Long id, DirectorDTO dto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Update : No Director with id: " + id);
                    return new RuntimeException("Update : No Director with id: " + id);
                });
        director.setName(dto.getLastName());
        director.setEmail(dto.getEmail());
        director.setPassword(dto.getPassword());

        Director updated = directorRepository.save(director);
        logger.info("Updated director id:" + updated.getId());
        return toDTO(updated);
    }

    @Override
    public void deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            logger.warn("Delete : No director with this id:" + id);
            throw new RuntimeException("Delete : No director with this id: " + id);
        }
        directorRepository.deleteById(id);
        logger.info("Deleted director id=" + id);
    }

    private DirectorDTO toDTO(Director director) {
        return new DirectorDTO(director.getId(), director.getName(), director.getEmail(), director.getPassword());
    }
}
