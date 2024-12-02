package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Project;
import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
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
public class ProjectService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    private static final String PROJECTS_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/proyectos-api/1.0.0/m/proyectos";

    public List<Project> getProjects() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PROJECTS_URL))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                List<Project> projects = new ArrayList<>();

                for (JsonNode node : rootNode) {
                    Project project = new Project();
                    project.setId(node.get("id").asText());
                    project.setName(node.get("nombre").asText());
                    project.setDescription(node.get("descripcion").asText());

                    projects.add(project);
                }

                return projects;
            } else {
                // Handle non-200 responses
                System.err.println("Failed to fetch projects: " + response.statusCode());
                return List.of();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list in case of an error
        }
    }

    public Project getProject(String project_id) {
        List<Project> projects = getProjects();
        for (Project project : projects) {
            if (project.getId().equals(project_id)) {
                return project;
            }
        }
        return null;
    }

    public List<Employee> getEmployees(String project_id) {
        return taskService.getEmployees(project_id);
    }

    public List<String> getEmployeeIds(String project_id) {
        return taskService.getEmployeeIds(project_id);
    }

}
