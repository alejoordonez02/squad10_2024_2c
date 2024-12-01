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

    public List<WorkLog> getWorkLogs() {
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

    public WorkLog addWorkLog(String employee_id, String task_id, double hours, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        WorkLog workLog = new WorkLog();
        workLog.setHours(hours);
        workLog.setTaskId(task_id);
        workLog.setDate(date);
        workLog.setEmployeeId(employee_id);
        return workLogRepository.save(workLog);
    }

    public double getWorkedHours(String project_id, String role_name, String role_experience, int year, int month) {
        String roleId = roleService.getRole(role_name, role_experience).getId();
        List<String> employeeIds = employeeService.getEmployees(roleId).stream().map(Employee::getId).collect(Collectors.toList());
        List<String> taskIds = taskService.getTasks(project_id, employeeIds).stream().map(Task::getId).collect(Collectors.toList());
        List<WorkLog> workLogs = getWorkLogs(year, month);
        double totalHours = 0;
        for (WorkLog workLog : workLogs) {
            if (taskIds.contains(workLog.getTaskId())) {
                totalHours += workLog.getHours();
            }
        }
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

    public double getWorkedHours(String employee_id, int year1, int month1, int day1, int year2, int month2, int day2) {
        LocalDate date1 = LocalDate.of(year1, month1, day1);
        LocalDate date2 = LocalDate.of(year2, month2, day2);
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, date1, date2);
        double totalHours = getTotalHours(workLogs);
        return totalHours;
    }

    public List<WorkLogDTO> getWorkedHoursPerDay(String employee_id, int year1, int month1, int day1, int year2, int month2, int day2) {
        LocalDate date1 = LocalDate.of(year1, month1, day1);
        LocalDate date2 = LocalDate.of(year2, month2, day2);
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, date1, date2);
        return workLogs.stream()
               .map(workLog -> new WorkLogDTO(workLog.getHours(), workLog.getDate()))
               .collect(Collectors.toList());
    }

}

