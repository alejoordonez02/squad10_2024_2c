package com.carga_horaria.carga_horaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class WorkLog {

// attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hours")
    private double hours;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

// init
    public WorkLog() {
    }

// getters
    public double getHours() {
        return hours;
    }

    public Task getTask() {
        return task;
    }

    public LocalDate getDate() {
        return date;
    }

// setters
    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}