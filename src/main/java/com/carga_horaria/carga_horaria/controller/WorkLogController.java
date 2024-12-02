package com.carga_horaria.carga_horaria.controller;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.dto.WorkLogDTO;
import com.carga_horaria.carga_horaria.service.WorkLogService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/work_logs")
public class WorkLogController {

    @Autowired
    private WorkLogService workLogService;

    @PostMapping
    @Operation(summary = "Agregar un worklog")
    public ResponseEntity<WorkLog> addWorkLog(@RequestBody WorkLog workLog) {
        return ResponseEntity.ok(workLogService.addWorkLog(workLog));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los worklogs")
    public ResponseEntity<List<WorkLog>> getWorkLogs() {
        return ResponseEntity.ok(workLogService.getWorkLogs());
    }

    @GetMapping("/roles")
    @Operation(summary = "Obtener las horas trabajadas por rol")
    public ResponseEntity<Double> getWorkedHours(
            @RequestParam String role_name,
            @RequestParam String role_experience,
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(workLogService.getWorkedHours(role_name, role_experience, year, month));
    }

    @GetMapping("/employees/{employee_id}")
    @Operation(summary = "Obtener las horas trabajadas por empleado en un mes")
    public ResponseEntity<Double> getWorkedHours(
            @PathVariable String employee_id,
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(workLogService.getWorkedHours(employee_id, year, month));
    }

    @GetMapping("/employees/{employee_id}/range")
    @Operation(summary = "Obtener las horas trabajadas por empleado en un rango de fechas")
    public ResponseEntity<Double> getWorkedHours(
            @PathVariable String employee_id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return ResponseEntity.ok(workLogService.getWorkedHours(employee_id, from, to));
    }

    @GetMapping("/employees/{employee_id}/range/per_day")
    @Operation(summary = "Obtener las horas trabajadas por día de un empleado en un rango de fechas")
    public ResponseEntity<List<WorkLogDTO>> getWorkedHoursPerDay(
            @PathVariable String employee_id,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return ResponseEntity.ok(workLogService.getWorkedHoursPerDay(employee_id, from, to));
    }

    // este método no tiene sentido salvo que devolver un worklog devuelva su id
    @DeleteMapping("/{work_log_id}")
    @Operation(summary = "Eliminar un worklog")
    public ResponseEntity<Void> deleteWorkLog(@PathVariable Long work_log_id) {
        workLogService.deleteWorkLog(work_log_id);
        return ResponseEntity.noContent().build();
    }

}
