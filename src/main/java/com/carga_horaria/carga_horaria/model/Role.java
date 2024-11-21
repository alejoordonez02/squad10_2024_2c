package com.carga_horaria.carga_horaria.model;

import java.util.ArrayList;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "experience")
    private String experience;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<Employee>();

// init
    public Role() {
    }

// getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

// setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
