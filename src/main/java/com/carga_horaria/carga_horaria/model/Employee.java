package com.carga_horaria.carga_horaria.model;

import java.util.List;

public class Employee {

// attributes
    private Long nid;
    private String firstName;
    private String lastName;
    private float salary;
    private WorkingHours workingHours;
    private List<Task> asignedTasks;

// init
    public Employee() {
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

    public float getSalary() {
        return salary;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
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

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    public void setAsignedTasks(List<Task> asignedTasks) {
        this.asignedTasks = asignedTasks;
    }

// putters
    public void addWorkedHours(float hours, Task task) {
        this.workingHours.addWorkedHours(hours, task);
    }

    public void asignTask(Task task) {
        this.asignedTasks.add(task);
    }

    public void asignTasks(List<Task> tasks) {
        this.asignedTasks.addAll(tasks);
    }
}
