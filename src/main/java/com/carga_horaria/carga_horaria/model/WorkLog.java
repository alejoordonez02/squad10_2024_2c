package com.carga_horaria.carga_horaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class WorkLog {

// attributes
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hours")
    private double hours;

    @Column(name = "taskId")
    private String taskId;

    @Column(name = "date")
    @Schema(description = "Fecha del trabajo en formato ISO", example = "2024-12-01")
    private LocalDate date;

    @Column(name = "employeeId")
    private String employeeId;

// init
    public WorkLog() {
    }

// getters
    public Long getId() {
        return id;
    }

    public double getHours() {
        return hours;
    }

    public String getTaskId() {
        return taskId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }


    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
