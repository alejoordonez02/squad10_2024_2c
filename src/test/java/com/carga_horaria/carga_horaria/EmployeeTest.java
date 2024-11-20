package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.WorkLog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class EmployeeTest {

    @Test
    void testGetAndSetNid() {
        Employee employee = new Employee();
        long nid = 44480134;
        employee.setNid(nid);
        assertEquals(nid, employee.getNid());
    }

    @Test
    void testGetAndSetFirstName() {
        Employee employee = new Employee();
        String firstName = "Alejo";
        employee.setFirstName(firstName);
        assertEquals(firstName, employee.getFirstName());
    }

    @Test
    void testGetAndSetLastName() {
        Employee employee = new Employee();
        String lastName = "Ordoñez";
        employee.setLastName(lastName);
        assertEquals(lastName, employee.getLastName());
    }

    @Test
    void testGetAndSetSalary() {
        Employee employee = new Employee();
        double salary = 1200000.0;
        employee.setSalary(salary);
        assertEquals(salary, employee.getSalary(), 0.01);
    }

    @Test
    void testGetAndSetWorkLogs() {
        Employee employee = new Employee();
        List<WorkLog> workLogs = List.of(new WorkLog(), new WorkLog());
        employee.setWorkLogs(workLogs);
        assertEquals(workLogs, employee.getWorkLogs());
    }

    @Test
    void testGetAndSetAssignedTasks() {
        Employee employee = new Employee();
        List<Task> assignedTasks = List.of(new Task(), new Task());
        employee.setAssignedTasks(assignedTasks);
        assertEquals(assignedTasks, employee.getAssignedTasks());
    }

    @Test
    void testAddWorkLog() {
        Employee employee = new Employee();
        WorkLog workLog = new WorkLog();
        employee.addWorkLog(workLog);
        assertTrue(employee.getWorkLogs().contains(workLog));
    }

    @Test
    void testAddWorkLogs() {
        Employee employee = new Employee();
        List<WorkLog> workLogs = List.of(new WorkLog(), new WorkLog());
        employee.addWorkLogs(workLogs);
        assertTrue(employee.getWorkLogs().containsAll(workLogs));
    }

    @Test
    void testAddTask() {
        Employee employee = new Employee();
        Task task = new Task();
        employee.addTask(task);
        assertTrue(employee.getAssignedTasks().contains(task));
    }

    @Test
    void testAddTasks() {
        Employee employee = new Employee();
        List<Task> tasks = List.of(new Task(), new Task());
        employee.addTasks(tasks);
        assertTrue(employee.getAssignedTasks().containsAll(tasks));
    }

}
