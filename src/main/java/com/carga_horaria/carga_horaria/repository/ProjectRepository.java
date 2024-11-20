package com.carga_horaria.carga_horaria.repository;

import com.carga_horaria.carga_horaria.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

