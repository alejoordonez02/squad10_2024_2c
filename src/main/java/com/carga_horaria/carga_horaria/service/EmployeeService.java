package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Role;
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
public class EmployeeService {

    @Autowired
    private RoleService roleService;

    private static final String EMPLOYEES_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/recursos-api/1.0.1/m/recursos";

    public List<Employee> getEmployees() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EMPLOYEES_URL))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                List<Employee> employees = new ArrayList<>();

                for (JsonNode node : rootNode) {
                    Employee employee = new Employee();
                    employee.setId(node.get("id").asText());
                    employee.setFirstName(node.get("nombre").asText());
                    employee.setLastName(node.get("apellido").asText());
                    employee.setNid(node.get("dni").asLong());

                    Role role = roleService.getRole(node.get("rolId").asText());
                    employee.setRole(role);

                    employees.add(employee);
                }

                return employees;
            } else {
                System.err.println("Error al cargar empleados: " + response.statusCode());
                return List.of();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Employee getEmployee(String employee_id) {
        List<Employee> employees = getEmployees();
        for (Employee employee : employees) {
            if (employee.getId().equals(employee_id)) {
                return employee;
            }
        }
        return null;
    }

}
