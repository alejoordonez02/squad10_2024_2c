package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.Project;
import com.carga_horaria.carga_horaria.model.Employee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void setAndGetTitle() {
        Task task = new Task();
        String title = "Task";
        task.setTitle(title);
        assertEquals(title, task.getTitle());
    }

    @Test
    void setAndGetDescription() {
        Task task = new Task();
        String description = "Description";
        task.setDescription(description);
        assertEquals(description, task.getDescription());
    }

    @Test
    void setAndGetProject() {
        Task task = new Task();
        Project project = new Project();
        task.setProject(project);
        assertEquals(project, task.getProject());
    }

    @Test
    void setAndGetAssignee() {
        Task task = new Task();
        Employee assignee = new Employee();
        task.setAssignee(assignee);
        assertEquals(assignee, task.getAssignee());
    }

}
