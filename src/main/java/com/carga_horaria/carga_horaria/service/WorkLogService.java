package com.carga_horaria.carga_horaria.service;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.repository.WorkLogRepository;
import com.carga_horaria.carga_horaria.repository.EmployeeRepository;
import com.carga_horaria.carga_horaria.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkLogService {

    @Autowired
    private WorkLogRepository workLogRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

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
        List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, year, month);
        WorkLog totalWorklog = getTotalWorkLog(workLogs);
        return totalWorklog;
    }

    public WorkLog addWorkLog(Long employee_id, Long task_id, double hours, int year, int month, int day) {
        Employee employee = employeeRepository.findById(employee_id).get();
        Task task = taskRepository.findById(task_id).get();
        LocalDate date = LocalDate.of(year, month, day);
        WorkLog workLog = new WorkLog();
        workLog.setHours(hours);
        workLog.setTask(task);
        workLog.setDate(date);
        employee.addWorkLog(workLog);
        // no sé si esto está guardando el employee...
        // si no habría que hacer también:
        // employeeRepository.save(employee);
        workLogRepository.save(workLog);
        return workLog;
    }

}

