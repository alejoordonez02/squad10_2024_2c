package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.Role;
import com.carga_horaria.carga_horaria.service.RoleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// [{"id":"1f14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Desarrollador","experiencia":"Senior"}
@RestController
@RequestMapping("/roles")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/{role_id}/{name}/{experience}")
    public ResponseEntity<Role> addRole(@PathVariable Long role_id, @PathVariable String name, @PathVariable String experience) {
        try {
            logger.info("Adding role with id: {}, name: {}, experience: {}", role_id, name, experience);
            Role role = roleService.addRole(role_id, name, experience);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            logger.error("Error adding role", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{role_id}")
    public ResponseEntity<Role> getRole(@PathVariable Long role_id) {
        try {
            logger.info("Fetching role with id: {}", role_id);
            Role role = roleService.getRole(role_id);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            logger.error("Error fetching role", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getRoles() {
        try {
            logger.info("Fetching all roles");
            List<Role> roles = roleService.getRoles();
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            logger.error("Error fetching roles", e);
            return ResponseEntity.status(500).body(null);
        }
    }

}
