package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.Project;
import com.carga_horaria.carga_horaria.model.Employee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testGetAndSetId() {
        Task task = new Task();
        String id = "task1";
        task.setId(id);
        assertEquals(id, task.getId());
    }

    @Test
    void testGetAndSetName() {
        Task task = new Task();
        String name = "Task Name";
        task.setName(name);
        assertEquals(name, task.getName());
    }

    @Test
    void testGetAndSetDescription() {
        Task task = new Task();
        String description = "Task Description";
        task.setDescription(description);
        assertEquals(description, task.getDescription());
    }

    @Test
    void testGetAndSetProjectId() {
        Task task = new Task();
        String projectId = "project1";
        task.setProjectId(projectId);
        assertEquals(projectId, task.getProjectId());
    }

    @Test
    void testGetAndSetAssigneeId() {
        Task task = new Task();
        String assigneeId = "assignee1";
        task.setAssigneeId(assigneeId);
        assertEquals(assigneeId, task.getAssigneeId());
    }

}
