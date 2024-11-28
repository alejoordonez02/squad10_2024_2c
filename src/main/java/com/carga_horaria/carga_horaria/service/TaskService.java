package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private EmployeeService employeeService;

    private static final String TASKS_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/tareas-api/1.0.0/m/tareas";

    public List<Task> getTasks() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TASKS_URL))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                List<Task> tasks = new ArrayList<>();

                for (JsonNode node : rootNode) {
                    Task task = new Task();
                    task.setId(node.get("id").asText());
                    task.setName(node.get("nombre").asText());
                    task.setDescription(node.get("descripcion").asText());
                    task.setProjectId(node.get("proyectoId").asText());
                    task.setAssigneeId(node.get("recursoId").asText());
                    tasks.add(task);
                }

                return tasks;
            } else {
                System.err.println("Error cargando tareas: " + response.statusCode());
                return List.of();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Task getTask(String taskId) {
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public List<Employee> getEmployees(String project_id) {
        List<Task> tasks = getTasks();
        List<String> employee_ids = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        for (Task task : tasks) {
            String assignee_id = task.getAssigneeId();
            if (task.getProjectId().equals(project_id) &&
                !employee_ids.contains(assignee_id)) {
                    employee_ids.add(assignee_id);
                    Employee employee = employeeService.getEmployee(assignee_id);
                    employees.add(employee);
            }
        }
        return employees;
    }

    public List<Task> getTasks(String project_id) {
        List<Task> tasks = getTasks();
        List<Task> projectTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId().equals(project_id)) {
                projectTasks.add(task);
            }
        }
        return projectTasks;
    }

    public List<Task> getTasks(String project_id, List<String> employeeIds) {
        List<Task> tasks = getTasks();
        List<Task> projectTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId().equals(project_id) &&
                employeeIds.contains(task.getAssigneeId())) {
                    projectTasks.add(task);
            }
        }
        return projectTasks;
    }

}
