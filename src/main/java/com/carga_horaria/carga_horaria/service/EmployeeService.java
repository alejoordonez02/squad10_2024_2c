package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Role;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.repository.EmployeeRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private WorkLog getTotalWorkLog(List<WorkLog> worklogs) {
        double totalHours = 0;
        for (WorkLog worklog : worklogs) {
            totalHours += worklog.getHours();
        }
        WorkLog totalWorkLog = new WorkLog();
        totalWorkLog.setHours(totalHours);
        return totalWorkLog;
    }

    public WorkLog getWorkLog(Long employee_id, int year, int month) {
        Employee employee = employeeRepository.findById(employee_id).get();
        List<WorkLog> worklogs = employee.getWorkLogs();
        List<WorkLog> filteredWorkLogs = worklogs.stream()
                .filter(worklog -> worklog.getDate().getYear() == year && worklog.getDate().getMonthValue() == month)
                .toList();
        WorkLog totalWorkLog = getTotalWorkLog(filteredWorkLogs);
        return totalWorkLog;
    }

    public Employee addEmployee(Long id, String first_name, String last_name, Long role_id) {
        Employee employee = new Employee();
        // employee.setId(id);
        employee.setFirstName(first_name);
        employee.setLastName(last_name);

        // Role role = roleRepository.findById(role_id).get();
        // employee.setRole(role);

        employeeRepository.save(employee);
        return employee;
    }

}
