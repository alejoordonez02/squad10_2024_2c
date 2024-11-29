package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.service.WorkLogService;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work_logs")
public class WorkLogController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/{employee_id}/{year}-{month}")
    // Obtener el total de horas trabajadas de un empleado en un mes y año específicos
    public ResponseEntity<Double> getWorkedHours(@PathVariable String employee_id,
                                             @PathVariable int year,
                                             @PathVariable int month) {
        return ResponseEntity.ok(workLogService.getWorkedHours(employee_id, year, month));
    }

    @GetMapping("/employee/{employee_id}/hours/week")
    public ResponseEntity<Double> getWorkedHoursInWeek(
        @PathVariable String employee_id,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(workLogService.getWorkedHoursInWeek(employee_id, startDate, endDate));
    }

    @PostMapping("/{employee_id}/{task_id}/{hours}/{year}-{month}-{day}")
    // Registrar las horas de trabajo de un empleado en una fecha y tarea específicas
    public ResponseEntity<WorkLog> addWorkLog(@PathVariable String employee_id,
                                              @PathVariable String task_id,
                                              @PathVariable double hours,
                                              @PathVariable int year,
                                              @PathVariable int month,
                                              @PathVariable int day) {
        return ResponseEntity.ok(workLogService.addWorkLog(employee_id, task_id, hours, year, month, day));
    }

    @GetMapping("/{proyect_id}/{role_name}/{role_experience}/{year}-{month}")
    // Obtener el total de horas trabajadas por un rol con un name y experiencia específicos en un proyecto, discriminada por tarea
    public ResponseEntity<Double> getWorkedHours(@PathVariable String project_id,
                                                 @PathVariable String role_name,
                                                 @PathVariable String role_experience,
                                                 @PathVariable int year,
                                                 @PathVariable int month) {
        return ResponseEntity.ok(workLogService.getWorkedHours(project_id, role_name, role_experience, year, month));
    }   

}
