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

    @Column(name = "salary")
    private double salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkLog> workLogs;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> assignedTasks;

// init
    public Employee() {
        this.workLogs = new ArrayList<>();
        this.assignedTasks = new ArrayList<>();
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

    public double getSalary() {
        return salary;
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

    public void setSalary(double salary) {
        this.salary = salary;
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
