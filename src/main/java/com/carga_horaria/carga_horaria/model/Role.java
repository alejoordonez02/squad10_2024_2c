package com.carga_horaria.carga_horaria.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// [{"id":"1f14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Desarrollador","experiencia":"Senior"}
// {"id":"3e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Desarrollador","experiencia":"Semi-Senior"}
// {"id":"4e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Desarrollador","experiencia":"Junior"}
// {"id":"5e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Analista","experiencia":"Nivel I"}
// {"id":"6e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Analista","experiencia":"Nivel II"}]

@Entity
public class Role {

// attributes
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "experience")
    private String experience;

    private List<String> employee_ids = new ArrayList<>();

// init
    public Role() {
    }

// getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public List<String> getEmployeeIds() {
        return employee_ids;
    }

// setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setEmployeeIds(List<String> employee_ids) {
        this.employee_ids = employee_ids;
    }

// putters
    public void addEmployeeId(String employee_id) {
        employee_ids.add(employee_id);
    }

}
