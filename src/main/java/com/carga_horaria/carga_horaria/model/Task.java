package com.carga_horaria.carga_horaria.model;

public class Task {

// init
    public Task() {
    }

// attributes
    private String title;
    private String description;
    private Project project;
    private Employee asignee;

// getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public Employee getAsignee() {
        return asignee;
    }

// setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setAsignee(Employee asignee) {
        this.asignee = asignee;
    }

}
