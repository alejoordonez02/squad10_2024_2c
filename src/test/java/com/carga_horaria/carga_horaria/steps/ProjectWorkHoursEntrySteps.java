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
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectWorkHoursEntrySteps {
    
    private Employee employee;
    private String startDate;
    private String endDate;
    private int totalWorkedHours;
    private List<WorkLog> workLogs = new ArrayList<>();
    private List<WorkLog> filteredWorkLogs = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private static int taskIdCounter = 1;
    private String errorMessage;

    @Autowired
    private WorkLogService workLogService;

    @BeforeEach
    public void setUp() {
        employee = null;
        startDate = "";
        endDate = "";
        totalWorkedHours = 0;
        workLogs.clear();
        filteredWorkLogs.clear();
        projects.clear();
        tasks.clear();
    }


    @Given("I have a project called {string} and tasks assigned")
    public void i_have_a_project_and_tasks_assigned(String projectName) {
        employee = createEmployee("Jhon Doe");
        Project project = createProject(projectName);
        Task task = createTaskForProject(project, employee);
        tasks.add(task);
        projects.add(project);
    }

    @Given("I have a project but I not have any tasks assigned")
    public void i_have_a_project_but_i_not_have_any_tasks_assigned() {
        employee = createEmployee("Jhon Doe");
        Project project = createProject("Unassigned Project");
        projects.add(project);
    }

    @Given("I have already logged {int} hours worked for the task {string} on {string}")
    public void i_have_already_logged_hours_worked_for_the_task_on(int hours, String taskName, String date) {
        Task task = tasks.stream()
            .filter(t -> t.getName().equals(taskName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        WorkLog workLog = createWorkLogForTask(employee.getId(), task.getId(), hours, LocalDate.parse(date));
        workLogs.add(workLog);
        totalWorkedHours += hours;
    }

    @And("I have logged {int} hours worked for the task {string} on {string}")
    public void i_have_logged_hours_worked_for_the_task_on(int hours, String taskName, String date) {
        Task task = tasks.stream()
            .filter(t -> t.getName().equals(taskName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    
        WorkLog workLog = createWorkLogForTask(employee.getId(), task.getId(), hours, LocalDate.parse(date));
        workLogs.add(workLog);
        totalWorkedHours += hours;
    }

    @When("I log {int} hours worked for the task {string} on {string}")
    public void i_log_hours_worked_for_the_task_on(int hours, String projectName, String date) {
        System.out.println("Projects: " + projects);
        Project project = projects.stream()
                                    .filter(p -> p.getName().equals(projectName))
                                    .findFirst()
                                    .orElse(null);
        System.out.println("Project: " + project.getName());
        if (project != null) { 
            String projectId = project.getId();
            Task task = tasks.stream()
                            .filter(t -> t.getProjectId().equals(projectId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Task not found"));

            WorkLog workLog = createWorkLogForTask(employee.getId(), task.getId(), hours, LocalDate.parse(date));
            workLogs.add(workLog);
            System.out.println(workLog.getHours());
            totalWorkedHours += workLog.getHours();
        }
        System.out.println("proyecto es null");
    }

    @When("I try to log {int} hours worked without specifying a task")
    public void i_try_to_log_hours_worked_without_specifying_a_task(Integer hours) {
        try {
            WorkLog workLog = createWorkLogForTask(employee.getId(), null, hours, LocalDate.now());
            workLogs.add(workLog); 
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("I try to log {int} hours worked for the task {string} on {string}")
    public void i_try_to_log_hours_worked_for_the_task_on_with_incorrect_date_format(Integer hours, String taskName, String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, formatter);

            Task task = tasks.stream()
                    .filter(t -> t.getName().equals(taskName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Task not found"));

            WorkLog workLog = createWorkLogForTask(employee.getId(), task.getId(), hours, date);
            workLogs.add(workLog);
        } catch (DateTimeParseException e) {
            errorMessage = "Incorrect date format";
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("I update the log to {int} hours for the task {string} on {string}")
    public void i_update_the_log_to_hours_for_the_task_on(int updatedHours, String taskName, String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        WorkLog existingLog = workLogs.stream()
            .filter(wl -> wl.getTaskId().equals(
                tasks.stream()
                    .filter(t -> t.getName().equals(taskName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Task not found"))
                    .getId()
                )
            )
            .filter(wl -> wl.getDate().equals(parsedDate))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("WorkLog not found for the given task and date"));

        totalWorkedHours -= existingLog.getHours(); 
        existingLog.setHours(updatedHours);       
        totalWorkedHours += updatedHours;         
    }

    @When("I access the logged hours for {string}")
    public void i_access_the_logged_hours_for(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        filteredWorkLogs = workLogs.stream()
            .filter(wl -> wl.getDate().equals(parsedDate))
            .collect(Collectors.toList());
    }

    @Then("the system should confirm that {int} hours were logged for the task {string} on {string}")
    public void the_system_should_confirm_that_hours_were_logged_for_the_task_on(int hours, String taskName, String date) {
        assertEquals(hours, totalWorkedHours, "The logged hours are incorrect for the task on the given date.");
    }

    @Then("the system should show an error message saying {string}")
    public void the_system_should_show_an_error_message_saying(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage, "The error message does not match.");
    }

    @Then("the system should update the logged hours for that task and date to {int} hours")
    public void the_system_should_update_the_logged_hours_for_that_task_and_date_to_hours(int expectedHours) {
        WorkLog updatedLog = workLogs.stream()
            .filter(wl -> wl.getTaskId().equals(
                tasks.get(0).getId()
            ))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("WorkLog not found"));

        assertEquals(expectedHours, updatedLog.getHours(), "Logged hours for the task were not updated correctly.");
    }

    @Then("the system should show {int} hours worked for the task {string} on that date")
    public void the_system_should_show_hours_worked_for_the_task_on_that_date(int expectedHours, String taskName) {
        WorkLog matchingWorkLog = filteredWorkLogs.stream()
            .filter(wl -> wl.getTaskId().equals(
                tasks.stream()
                    .filter(t -> t.getName().equals(taskName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Task not found hen"))
                    .getId()
                )
            )
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("WorkLog not found for the given task and date"));
    
        assertEquals(expectedHours, matchingWorkLog.getHours(), "The displayed hours for the task are incorrect.");
    }

    @And("the total worked hours for the project should be updated")
    public void the_total_worked_hours_for_the_project_should_be_updated() {
        Double totalHoursForProject = workLogs.stream()
                                        .filter(wl -> tasks.stream()
                                        .anyMatch(t -> t.getId().equals(wl.getTaskId())))
                                        .mapToDouble(WorkLog::getHours)
                                        .sum();
        System.out.println("Total worked hours for the project: " + totalHoursForProject);
        assertTrue(totalHoursForProject > 0, "Total worked hours for the project should be greater than zero");
    }

    @And("the hours should not be logged")
    public void the_hours_should_not_be_logged() {
        assertTrue(workLogs.isEmpty(), "No hours should be logged.");
    }

    @And("the total worked hours should reflect the change")
    public void the_total_worked_hours_should_reflect_the_change() {
        double totalHoursForProject = workLogs.stream()
            .filter(wl -> tasks.stream()
            .anyMatch(t -> t.getId().equals(wl.getTaskId())))
            .mapToDouble(WorkLog::getHours)
            .sum();

        assertTrue(totalHoursForProject > 0, "Total worked hours for the project should be greater than zero.");
    }

    private Employee createEmployee(String name) {
        Employee employee = new Employee();
        employee.setId(String.valueOf(name.hashCode()));
        employee.setFirstName(name.split(" ")[0]);
        employee.setLastName(name.split(" ")[1]);
        return employee;
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
        task.setName(project.getName());
        task.setAssigneeId(employee.getId());
        task.setProjectId(project.getId()); 
        return task;
    }
    
    private WorkLog createWorkLogForTask(String employeeId, String taskId, int hours, LocalDate date) {
        if (taskId == null){
            throw new IllegalArgumentException("Task is required");
        }
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        workLog.setTaskId(taskId);
        workLog.setDate(date);
        return workLog;
    }

}
