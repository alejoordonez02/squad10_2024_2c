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

// [{"id":"ff14a491-e26d-4092-86ea-d76f20c165d1","nombre":"Martin","apellido":"Garcia","dni":"33834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"2e6ecd47-fa18-490e-b25a-c9101a398b6d","nombre":"Lucia","apellido":"Perez","dni":"33834235","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"47f744bb-0553-497a-b6e3-fdb64ddaca2a","nombre":"Mariana","apellido":"Juarez","dni":"31834234","rolId":"1f14a491-e26d-4092-86ea-d76f20c165d1"}
// {"id":"a21147f8-6538-46d8-95ac-9ddf95ff8c29","nombre":"Horacio","apellido":"Martinez","dni":"30834236","rolId":"6e6ecd47-fa18-490e-b25a-c9101a398b6d"}]
@Entity
public class Employee {

// attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nid")
    private Long nid;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkLog> workLogs= new ArrayList<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> assignedTasks = new ArrayList<>();

// init
    public Employee() {
    }

// getters
    public Long getId() {
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

    public Role getRole() {
        return role;
    }

    public List<WorkLog> getWorkLogs() {
        return workLogs;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

// setters
    public void setId(Long id) {
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

    public void setRole(Role role) {
        this.role = role;
    }

    public void setWorkLogs(List<WorkLog> workLogs) {
        this.workLogs = workLogs;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

// putters
    public void addWorkLog(WorkLog workLog) {
        this.workLogs.add(workLog);
    }

    public void addWorkLogs(List<WorkLog> workLogs) {
        this.workLogs.addAll(workLogs);
    }

    public void addTask(Task task) {
        this.assignedTasks.add(task);
    }

    public void addTasks(List<Task> tasks) {
        this.assignedTasks.addAll(tasks);
    }

}
