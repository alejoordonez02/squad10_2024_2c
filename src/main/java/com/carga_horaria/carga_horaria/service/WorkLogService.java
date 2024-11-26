package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.repository.WorkLogRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkLogService {

    @Autowired
    private WorkLogRepository workLogRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaskService taskService;

    private double getTotalHours(List<WorkLog> worklogs) {
        double totalHours = 0;
        for (WorkLog worklog : worklogs) {
            totalHours += worklog.getHours();
        }
        return totalHours;
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

}

