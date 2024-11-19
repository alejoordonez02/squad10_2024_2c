package com.carga_horaria.carga_horaria.model;

import java.time.LocalDate;

public class WorkLog {

// attributes
    private double hours;
    private Task task;
    private LocalDate date;

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