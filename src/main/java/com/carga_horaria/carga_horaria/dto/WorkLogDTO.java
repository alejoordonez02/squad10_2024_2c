package com.carga_horaria.carga_horaria.dto;

import java.time.LocalDate;

public class WorkLogDTO {

// attributes
    private double hours;
    private LocalDate date;

// init
    public WorkLogDTO(double hours, LocalDate date) {
        this.hours = hours;
        this.date = date;
    }

// getters
    public double getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }

// setters
    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
