package com.carga_horaria.carga_horaria.dto;

import java.time.LocalDate;

public class WorkLogDTO {

// attributes
    private double hours;
    private String taskId;
    private LocalDate date;

// init
    public WorkLogDTO(double hours, String taskId, LocalDate date) {
        this.hours = hours;
        this.taskId = taskId;
        this.date = date;
    }

// getters
    public double getHours() {
        return hours;
    }

    public String getTaskId() {
        return taskId;
    }

    public LocalDate getDate() {
        return date;
    }

// setters
    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
