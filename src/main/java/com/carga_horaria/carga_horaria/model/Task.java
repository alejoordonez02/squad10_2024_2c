package com.carga_horaria.carga_horaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


// [{"id":"f635b4ca-c091-472c-8b5a-cb3086d1973","nombre":"Diseño de la Base de Datos","descripcion":"Definir la...","recursoId":"ff14a491-e26d-4092-86ea-d76f20c165d1","proyectoId":"a6e2167f-67a1-4f60-b9e9-6bae7bc3a15"}
@Entity
public class Task {

// attributes
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private String project_id;

    private String assignee_id;

// init
    public Task() {
    }

// getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectId() {
        return project_id;
    }

    public String getAssigneeId() {
        return assignee_id;
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

    public void setProjectId(String project_id) {
        this.project_id = project_id;
    }

    public void setAssigneeId(String assignee_id) {
        this.assignee_id = assignee_id;
    }

}
