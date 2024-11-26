package com.carga_horaria.carga_horaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// [{"id":"a6e2167f-67a1-4f60-b9e9-6bae7bc3a15","nombre":"Sistema ","descripcion":", incluir integración con otras áreas, como ventas y compras, y permite la optimización de recursos."}

@Entity
public class Project {

// attributes
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

// init
    public Project() {
    }

// getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Task task : tasks) {
            employees.add(task.getAssignee());
        }
        return employees;
    }

// setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
