package com.carga_horaria.carga_horaria.model;

import java.util.List;

public class Project {

// attributes
    private String name;
    private List<Task> tasks;

// init
    public Project() {
    }

// getters
    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

// setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

// putters
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

}
