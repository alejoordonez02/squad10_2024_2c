package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.model.Role;

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
    void testGetAndSetAssignedTaskIds() {
        Employee employee = new Employee();
        List<String> assignedTaskIds = List.of("task1", "task2");
        employee.setAssignedTaskIds(assignedTaskIds);
        assertEquals(assignedTaskIds, employee.getAssignedTasks());
    }

    @Test
    void testAddWorkLogId() {
        Employee employee = new Employee();
        String workLogId = "workLog1";
        employee.addWorkLogId(workLogId);
        assertTrue(employee.getWorkLogs().contains(workLogId));
    }

    @Test
    void testAddWorkLogIds() {
        Employee employee = new Employee();
        List<String> workLogIds = List.of("workLog1", "workLog2");
        employee.addWorkLogIds(workLogIds);
        assertTrue(employee.getWorkLogs().containsAll(workLogIds));
    }

    @Test
    void testAddTaskId() {
        Employee employee = new Employee();
        String taskId = "task1";
        employee.addTask(taskId);
        assertTrue(employee.getAssignedTasks().contains(taskId));
    }

    @Test
    void testAddTaskIds() {
        Employee employee = new Employee();
        List<String> taskIds = List.of("task1", "task2");
        employee.addTasks(taskIds);
        assertTrue(employee.getAssignedTasks().containsAll(taskIds));
    }

}
