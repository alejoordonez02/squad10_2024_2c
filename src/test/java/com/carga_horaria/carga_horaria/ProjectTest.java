package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Project;
import com.carga_horaria.carga_horaria.model.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ProjectTest {

    @Test
    void testGetAndSetName() {
        Project project = new Project();
        String name = "Project";
        project.setName(name);
        assertEquals(name, project.getName());
    }

    @Test
    void testGetAndSetTasks() {
        Project project = new Project();
        List<Task> tasks = List.of(new Task(), new Task());
        project.setTasks(tasks);
        assertEquals(tasks, project.getTasks());
    }

    @Test
    void testAddTask() {
        Project project = new Project();
        Task task = new Task();
        project.addTask(task);
        assertTrue(project.getTasks().contains(task));
    }

    @Test
    void testAddTasks() {
        Project project = new Project();
        List<Task> tasks = List.of(new Task(), new Task());
        project.addTasks(tasks);
        assertTrue(project.getTasks().containsAll(tasks));
    }

}
