package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.service.WorkLogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work_logs")
public class WorkLogController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/{employee_id}/{year}-{month}")
    // Obtener el registro de horas de trabajo de un empleado en un mes y año específicos
    public ResponseEntity<WorkLog> getWorkLog(Long employee_id, int year, int month) {
        return ResponseEntity.ok(workLogService.getWorkLog(employee_id, year, month));
    }

    @PostMapping("/{employee_id}/{task_id}/{hours}/{year}-{month}-{day}")
    // Registrar las horas de trabajo de un empleado en una fecha y tarea específicas
    public ResponseEntity<WorkLog> addWorkLog(Long employee_id, Long task_id, double hours, int year, int month, int day) {
        return ResponseEntity.ok(workLogService.addWorkLog(employee_id, task_id, hours, year, month, day));
    }

}
