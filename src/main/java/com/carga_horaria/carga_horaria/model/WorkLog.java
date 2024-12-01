package com.carga_horaria.carga_horaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WorkLog {

// attributes
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hours")
    private double hours;

    @JsonIgnore
    @Column(name = "task_id")
    private String task_id;

    @Column(name = "date")
    private LocalDate date;

    @JsonIgnore
    @Column(name = "employee_id")
    private String employee_id;

// init
    public WorkLog() {
    }

// getters
    public double getHours() {
        return hours;
    }

    public String getTaskId() {
        return task_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmployeeId() {
        return employee_id;
    }

// setters
    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setEmployeeId(String employee_id) {
        this.employee_id = employee_id;
    }

}