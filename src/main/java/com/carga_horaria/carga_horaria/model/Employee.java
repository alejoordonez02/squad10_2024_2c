package com.carga_horaria.carga_horaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// [{"id":"ff14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Martin","apellido":"Garcia","dni":"33834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"2e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Lucia","apellido":"Perez","dni":"33834235","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"47f744bb-0553-497a-b6e3-fdb64ddaca2a","nombre":"Mariana","apellido":"Juarez","dni":"31834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"a21147f8-6538-46d8-95ac-9ddf95ff8c29","nombre":"Horacio","apellido":"Martinez","dni":"30834236","rolId":"6e6ecd47-fa18-490e-b25a-c9101a398b6d"}]
@Entity
public class Employee {

// attributes
    @Id
    private String id;

    @Column(name = "nid")
    private Long nid;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    private String role_id;

    private List<String> workLog_ids = new ArrayList<>();

    private List<String> assignedTask_ids = new ArrayList<>();

// init
    public Employee() {
    }

// getters
    public String getId() {
        return id;
    }

    public Long getNid() {
        return nid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role_id;
    }

    public List<String> getWorkLogs() {
        return workLog_ids;
    }

    public List<String> getAssignedTasks() {
        return assignedTask_ids;
    }

// setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoleId(String role_id) {
        this.role_id = role_id;
    }

    public void setWorkLogIds(List<String> workLog_ids) {
        this.workLog_ids = workLog_ids;
    }

    public void setAssignedTaskIds(List<String> assignedTask_ids) {
        this.assignedTask_ids = assignedTask_ids;
    }

// putters
    public void addWorkLogId(String workLog_id) {
        this.workLog_ids.add(workLog_id);
    }

    public void addWorkLogIds(List<String> workLog_ids) {
        this.workLog_ids.addAll(workLog_ids);
    }

    public void addTask(String task_id) {
        this.assignedTask_ids.add(task_id);
    }

    public void addTasks(List<String> task_ids) {
        this.assignedTask_ids.addAll(task_ids);
    }

}
