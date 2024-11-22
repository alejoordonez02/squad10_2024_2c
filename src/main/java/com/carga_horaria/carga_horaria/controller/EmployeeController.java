package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// [{"id":"ff14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Martin","apellido":"Garcia","dni":"33834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/{employee_id}/{first_name}/{last_name}/{role_id}")
    public ResponseEntity<Employee> addEmployee(Long employee_id, String first_name, String last_name, Long role_id) {
        return ResponseEntity.ok(employeeService.addEmployee(employee_id, first_name, last_name, role_id));
    }

}