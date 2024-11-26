package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Role;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private static final String ROLES_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/32c8fe38-22a6-4fbb-b461-170dfac937e4/roles-api/1.0.0/m/roles";

    public List<Role> getRoles() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ROLES_URL))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                List<Role> roles = new ArrayList<>();

                for (JsonNode node : rootNode) {
                    Role role = new Role();
                    role.setId(node.get("id").asText());
                    role.setName(node.get("nombre").asText());
                    role.setExperience(node.get("experiencia").asText());

                    roles.add(role);
                }

                return roles;
            } else {
                System.err.println("Error cargando roles: " + response.statusCode());
                return List.of();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Role getRole(String role_id) {
        List<Role> roles = getRoles();
        for (Role role : roles) {
            if (role.getId().equals(role_id)) {
                return role;
            }
        }
        return null;
    }

}
