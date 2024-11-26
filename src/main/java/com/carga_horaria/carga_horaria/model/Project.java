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

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private List<String> task_ids = new ArrayList<>();

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

    public List<String> getTaskIds() {
        return task_ids;
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

    public void setTaskIds(List<String> task_ids) {
        this.task_ids = task_ids;
    }

// putters
    public void addTaskId(String task_id) {
        this.task_ids.add(task_id);
    }

    public void addTaskIds(List<String> task_ids) {
        this.task_ids.addAll(task_ids);
    }

}
