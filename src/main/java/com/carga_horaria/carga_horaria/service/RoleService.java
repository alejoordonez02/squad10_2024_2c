package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Role;
import com.carga_horaria.carga_horaria.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Long role_id, String name, String experience) {
        Role role = new Role();
        role.setId(role_id);
        role.setName(name);
        role.setExperience(experience);
        return roleRepository.save(role);
    }

    public Role getRole(Long role_id) {
        Optional<Role> role = roleRepository.findById(role_id);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new RuntimeException("Role not found with id: " + role_id);
        }
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}
