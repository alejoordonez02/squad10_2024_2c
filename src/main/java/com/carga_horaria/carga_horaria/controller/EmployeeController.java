package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// [{"id":"ff14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Martin","apellido":"Garcia","dni":"33834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

}