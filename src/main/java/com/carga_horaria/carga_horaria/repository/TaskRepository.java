package com.carga_horaria.carga_horaria.repository;

import com.carga_horaria.carga_horaria.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}