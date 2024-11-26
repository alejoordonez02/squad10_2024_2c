package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Project;
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
    void testGetAndSetDescription() {
        Project project = new Project();
        String description = "Description";
        project.setDescription(description);
        assertEquals(description, project.getDescription());
    }

    @Test
    void testGetAndSetTaskIds() {
        Project project = new Project();
        List<String> taskIds = List.of("task1", "task2");
        project.setTaskIds(taskIds);
        assertEquals(taskIds, project.getTaskIds());
    }

    @Test
    void testAddTaskId() {
        Project project = new Project();
        String taskId = "task1";
        project.addTaskId(taskId);
        assertTrue(project.getTaskIds().contains(taskId));
    }

    @Test
    void testAddTaskIds() {
        Project project = new Project();
        List<String> taskIds = List.of("task1", "task2");
        project.addTaskIds(taskIds);
        assertTrue(project.getTaskIds().containsAll(taskIds));
    }

}
