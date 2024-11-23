package com.carga_horaria.carga_horaria.repository;

import com.carga_horaria.carga_horaria.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

