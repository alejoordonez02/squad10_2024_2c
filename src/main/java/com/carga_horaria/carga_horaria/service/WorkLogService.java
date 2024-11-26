// package com.carga_horaria.carga_horaria.service;

// import com.carga_horaria.carga_horaria.model.WorkLog;
// import com.carga_horaria.carga_horaria.model.Employee;
// import com.carga_horaria.carga_horaria.model.Task;
// import com.carga_horaria.carga_horaria.repository.WorkLogRepository;

// import java.time.LocalDate;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class WorkLogService {

//     @Autowired
//     private WorkLogRepository workLogRepository;

//     @Autowired
//     private EmployeeService employeeService;

//     @Autowired
//     private TaskService taskService;

//     private WorkLog getTotalWorkLog(List<WorkLog> worklogs) {
//         double totalHours = 0;
//         for (WorkLog worklog : worklogs) {
//             totalHours += worklog.getHours();
//         }
//         WorkLog totalWorkLog = new WorkLog();
//         totalWorkLog.setHours(totalHours);
//         return totalWorkLog;
//     }

//     public WorkLog getWorkLog(Long employee_id, int year, int month) {
//         List<WorkLog> workLogs = workLogRepository.findWorkLog(employee_id, year, month);
//         WorkLog totalWorklog = getTotalWorkLog(workLogs);
//         return totalWorklog;
//     }

//     public WorkLog addWorkLog(Long employee_id, Long task_id, double hours, int year, int month, int day) {
//         Employee employee = employeeService.getEmployee(employee_id);
//         Task task = taskService.getTask(task_id);
//         LocalDate date = LocalDate.of(year, month, day);
//         WorkLog workLog = new WorkLog();
//         workLog.setHours(hours);
//         workLog.setTask(task);
//         workLog.setDate(date);
//         employee.addWorkLog(workLog);
//         employeeService.updateEmployee(employee);
//         workLogRepository.save(workLog);
//         return workLog;
//     }

// }

