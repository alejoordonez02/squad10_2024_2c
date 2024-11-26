package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.Project;
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
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

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

                    Project project = projectService.getProject(node.get("proyectoId").asText());
                    task.setProject(project);

                    Employee assignee = employeeService.getEmployee(node.get("recursoId").asText());
                    task.setAssignee(assignee);

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
}
