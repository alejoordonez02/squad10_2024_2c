package com.carga_horaria.carga_horaria.steps;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import com.carga_horaria.carga_horaria.model.*;
import com.carga_horaria.carga_horaria.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeWorkHoursInquirySteps {

    private Employee employee;
    private String startDate;
    private String endDate;
    private int totalWorkedHours;
    private List<WorkLog> workLogs = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private static int taskIdCounter = 1;

    @Autowired
    private WorkLogService workLogService;

    @BeforeEach
    public void setUp() {
        employee = null;
        startDate = "";
        endDate = "";
        totalWorkedHours = 0;
        workLogs.clear();
        projects.clear();
        tasks.clear();
    }

    @Given("I am a operation manager in the system")
    public void i_am_a_operations_manager_in_the_system() {}

    @And("the employee {string} has not logged any hours in the period {string} to {string}")
    public void employee_that_not_have_worked_hours(String employeeName, String startDate, String endDate){
        employee = createEmployee(employeeName);
    }

    @When("I request the worked hours for the employee {string} from {string} to {string}")
    public void i_request_the_worked_hours_for_the_employee_from_to(String employeeName, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        employee = createEmployee(employeeName);
        WorkLog workLog = createWorkLog(employee.getId(), 20, LocalDate.parse(startDate));
        workLogs.add(workLog);
        List<WorkLog> workLogsForEmployee = findWorkLogsForEmployeeOnProjectInRange(employee.getId(), startDate, endDate);

        for (WorkLog worklog : workLogsForEmployee) {
            totalWorkedHours += worklog.getHours();
        } 
    }

    @When("I request the worked hours for the employee {string} for the project {string} from {string} to {string}")
    public void i_request_the_worked_hours_for_the_employee_for_the_project_from_to(String employeeName, String projectName, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        employee = createEmployee(employeeName);
        Project project = createProject(projectName);
        projects.add(project);
        Task task = createTaskForProject(project, employee);
        tasks.add(task);
        
        WorkLog workLog = createWorkLogForTask(employee.getId(),task.getId(), 20, LocalDate.parse(startDate));
        workLogs.add(workLog);

        List<WorkLog> workLogsForEmployeeOnProject = findWorkLogsForEmployeeOnProjectInRange(employee.getId(), projectName, startDate, endDate);

        for (WorkLog worklog : workLogsForEmployeeOnProject) {
            totalWorkedHours += worklog.getHours();
        }
    }

    @When("I request the worked hours from the employee {string} from {string} to {string}")
    public void i_request_the_worked_hours_from_the_employee_from_to(String employeeName, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        List<WorkLog> workLogsForEmployee = findWorkLogsForEmployeeOnProjectInRange(employee.getId(), startDate, endDate);

        for (WorkLog worklog : workLogsForEmployee) {
            totalWorkedHours += worklog.getHours();
        } 
    }

    @Then("the system should display the total hours worked by {string} for the period {string} to {string}")
    public void the_system_should_display_the_total_hours_worked_by_for_the_period_to(String expectedEmployeeName, String expectedStartDate, String expectedEndDate) {
        String employeeFullName = employee.getFirstName() + " " + employee.getLastName();
        assertEquals(employeeFullName, expectedEmployeeName, "Employee name does not match");
        assertEquals(startDate, expectedStartDate, "Start date does not match");
        assertEquals(endDate, expectedEndDate, "End date does not match");
        assertTrue(totalWorkedHours > 0, "Total worked hours should be greater than zero");
        
        System.out.println("Total hours worked by " + employeeFullName + " from " + startDate + " to " + endDate + " is: " + totalWorkedHours);
    }

    @Then("the system should display the total hours worked by {string} on {string} in the period {string} to {string}")
    public void the_system_should_display_the_total_hours_worked_by_on_in_the_period_to(String expectedEmployeeName, String expectedProjectName, String expectedStartDate, String expectedEndDate) {
        String employeeFullName = employee.getFirstName() + " " + employee.getLastName();
        String projectName = projects.get(0).getName();
        assertEquals(employeeFullName, expectedEmployeeName, "Employee name does not match");
        assertEquals(projectName, expectedProjectName, "Project name does not match");
        assertEquals(startDate, expectedStartDate, "Start date does not match");
        assertEquals(endDate, expectedEndDate, "End date does not match");
        assertTrue(totalWorkedHours > 0, "Total worked hours should be greater than zero");
        
        System.out.println("Total hours worked by " + employeeFullName + " on project " + projectName + " from " + startDate + " to " + endDate + " is: " + totalWorkedHours);
    }
    @Then("the system should display {int} hours worked for {string} in the period {string} to {string}")
    public void the_system_should_display_zero_hours_for_employee_in_the_period(int expectedHours, String expectedEmployeeName, String expectedStartDate, String expectedEndDate) {
        String employeeFullName = employee.getFirstName() + " " + employee.getLastName();
        assertEquals(employeeFullName, expectedEmployeeName, "Employee name does not match");
        assertEquals(startDate, expectedStartDate, "Start date does not match");
        assertEquals(endDate, expectedEndDate, "End date does not match");
        assertEquals(expectedHours, totalWorkedHours, "Total worked hours should be " + expectedHours);
        
        System.out.println("Total hours worked by " + employeeFullName + " from " + startDate + " to " + endDate + " is: " + totalWorkedHours);
    }




    private Employee createEmployee(String name) {
        Employee employee = new Employee();
        employee.setId(String.valueOf(name.hashCode()));
        employee.setFirstName(name.split(" ")[0]);
        employee.setLastName(name.split(" ")[1]);
        return employee;
    }

    private WorkLog createWorkLog(String employeeId, int hours, LocalDate date) {
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        String taskId = "task" + taskIdCounter++;
        workLog.setTaskId(taskId);
        workLog.setDate(date);
        return workLog;
    }
    private WorkLog createWorkLogForTask(String employeeId, String taskId, int hours, LocalDate date) {
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        workLog.setTaskId(taskId);
        workLog.setDate(date);
        return workLog;
    }

    private Project createProject(String projectName) {
        Project project = new Project();
        project.setId(String.valueOf(projectName.hashCode()));
        project.setName(projectName);
        return project;
    }

    private Task createTaskForProject(Project project, Employee employee) {
        Task task = new Task();
        task.setId("task" + taskIdCounter++);
        task.setName("Task for " + project.getName());
        task.setAssigneeId(employee.getId());
        task.setProjectId(project.getId()); 
        return task;
    }

    private List<WorkLog> findWorkLogsForEmployeeOnProjectInRange(String employeeId, String startDate, String endDate) {
        List<WorkLog> workLogsForEmployee = new ArrayList<>();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);     

        for (WorkLog workLog : workLogs) {
                if (workLog.getEmployeeId().equals(employeeId) &&
                    !workLog.getDate().isBefore(start) &&
                    !workLog.getDate().isAfter(end)) {
                    workLogsForEmployee.add(workLog);
                }            
        }
        return workLogsForEmployee;
    }

        private List<WorkLog> findWorkLogForEmployeeOnProjectInMonth(String employeeId, String projectName, String month) {
        List<WorkLog> workLogsForEmployee = new ArrayList<>();

        Project project = projects.stream()
                                  .filter(p -> p.getName().equals(projectName))
                                  .findFirst()
                                  .orElse(null);
    
        if (project != null) {
            String projectId = project.getId();
    
            for (WorkLog workLog : workLogs) {
                Task task = tasks.stream()
                                 .filter(t -> t.getId().equals(workLog.getTaskId()))
                                 .findFirst()
                                 .orElse(null);
    
                if (task != null && task.getProjectId().equals(projectId) &&
                    workLog.getEmployeeId().equals(employeeId) &&
                    workLog.getDate().getMonthValue() == Integer.parseInt(month.split("-")[1]) &&
                    workLog.getDate().getYear() == Integer.parseInt(month.split("-")[0])) {
                    workLogsForEmployee.add(workLog); 
                }
            }
        }
    
        return workLogsForEmployee;
    }

    private List<WorkLog> findWorkLogsForEmployeeOnProjectInRange(String employeeId, String projectName, String startDate, String endDate) {
        List<WorkLog> workLogsForEmployee = new ArrayList<>();

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Project project = projects.stream()
                                  .filter(p -> p.getName().equals(projectName))
                                  .findFirst()
                                  .orElse(null);
    
        if (project != null) {
            String projectId = project.getId();  

            for (WorkLog workLog : workLogs) {
                Task task = tasks.stream()
                                 .filter(t -> t.getId().equals(workLog.getTaskId()))
                                 .findFirst()
                                 .orElse(null);
                if (task != null && task.getProjectId().equals(projectId) &&
                    workLog.getEmployeeId().equals(employeeId) &&
                    !workLog.getDate().isBefore(start) &&
                    !workLog.getDate().isAfter(end)) {
                    workLogsForEmployee.add(workLog);
                }
            }
        }
        return workLogsForEmployee;
    }
}