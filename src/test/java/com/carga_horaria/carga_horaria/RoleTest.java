package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Role;
import com.carga_horaria.carga_horaria.model.Employee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class RoleTest {

    @Test
    void testGetAndSetName() {
        Role role = new Role();
        String name = "Role";
        role.setName(name);
        assertEquals(name, role.getName());
    }

    @Test
    void testGetAndSetExperience() {
        Role role = new Role();
        String experience = "Experience";
        role.setExperience(experience);
        assertEquals(experience, role.getExperience());
    }

    // @Test
    // void testGetAndSetEmployees() {
    //     Role role = new Role();
    //     List<Employee> employees = List.of(new Employee(), new Employee());
    //     role.setEmployees(employees);
    //     assertEquals(employees, role.getEmployees());
    // }

}
