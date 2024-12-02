package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.dto.WorkLogDTO;
import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.Role;
import com.carga_horaria.carga_horaria.repository.WorkLogRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

@Service
public class WorkLogService {

    @Autowired
    private WorkLogRepository workLogRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TaskService taskService;

    private double getTotalHours(List<WorkLog> worklogs) {
        double totalHours = 0;
        for (WorkLog worklog : worklogs) {
            totalHours += worklog.getHours();
        }
        return totalHours;
    }

    public WorkLog addWorkLog(WorkLog workLog) {
        // TODO: validate workLog
        return workLogRepository.save(workLog);
    }

    public List<WorkLog> getWorkLogs() {
        // devolver todos los worklogs
        return workLogRepository.findAll();
    }

    public List<WorkLog> getWorkLogs(int year, int month) {
        return workLogRepository.findWorkLog(year, month);
    }

    public double getWorkedHours(String employee_id, int year, int month) {
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, year, month);
        double totalHours = getTotalHours(workLogs);
        return totalHours;
    }

    public double getWorkedHours(String role_name, String role_experience, int year, int month) {
        String roleId = roleService.getRole(role_name, role_experience).getId();
        List<String> employeeIds = employeeService.getEmployees(roleId).stream().map(Employee::getId).collect(Collectors.toList());
        List<WorkLog> workLogs = getWorkLogs(year, month);
        double totalHours = 0;
        for (WorkLog workLog : workLogs) {
            if (employeeIds.contains(workLog.getEmployeeId())) {
                totalHours += workLog.getHours();
            }
        }
        return totalHours;
    }

    public double getWorkedHours(String role_name, String role_experience, String project_id, int year, int month) {
        String roleId = roleService.getRole(role_name, role_experience).getId();
        List<String> employeeIdsFilteredByProject = taskService.getEmployeeIds(project_id);
        List<String> employeeIdsFilteredByRole = employeeService.getEmployees(roleId).stream().map(Employee::getId).collect(Collectors.toList());
        List<WorkLog> workLogs = getWorkLogs(year, month);
        double totalHours = 0;
        for (WorkLog workLog : workLogs) {
            if (employeeIdsFilteredByProject.contains(workLog.getEmployeeId()) &&
                employeeIdsFilteredByRole.contains(workLog.getEmployeeId())) {
                totalHours += workLog.getHours();
            }
        }
        return totalHours;
    }

    public double getWorkedHours(String employee_id, LocalDate from, LocalDate to) {
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, from, to);
        double totalHours = getTotalHours(workLogs);
        return totalHours;
    }

    public List<WorkLogDTO> getWorkedHoursPerDay(String employee_id, LocalDate from, LocalDate to) {
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, from, to);
        return workLogs.stream()
               .map(workLog -> new WorkLogDTO(workLog.getHours(), workLog.getTaskId(), workLog.getDate()))
               .collect(Collectors.toList());
    }

    public void deleteWorkLog(Long id) {
        workLogRepository.deleteById(id);
    }

}

