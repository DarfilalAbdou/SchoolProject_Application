package com.Project.school.repository;

import com.Project.school.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    List<Director>findByLastName(String lastName);
    Optional<Director> findByEmail(String email);

}