package com.carga_horaria.carga_horaria.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

// attributes
    private Long nid;
    private String firstName;
    private String lastName;
    private double salary;
    private List<WorkLog> workLogs;
    private List<Task> asignedTasks;

// init
    public Employee() {
        this.workLogs = new ArrayList<WorkLog>();
    }

// getters
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

    public List<Task> getAsignedTasks() {
        return asignedTasks;
    }

// setters
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

    public void setAsignedTasks(List<Task> asignedTasks) {
        this.asignedTasks = asignedTasks;
    }

// putters
    public void addWorkLog(WorkLog workLog) {
        this.workLogs.add(workLog);
    }

    public void addWorkLogs(List<WorkLog> workLogs) {
        this.workLogs.addAll(workLogs);
    }

    public void asignTask(Task task) {
        this.asignedTasks.add(task);
    }

    public void asignTasks(List<Task> tasks) {
        this.asignedTasks.addAll(tasks);
    }

}
