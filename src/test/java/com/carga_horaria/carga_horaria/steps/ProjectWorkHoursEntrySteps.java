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
import java.util.ArrayList;
import java.util.List;

public class ProjectWorkHoursEntrySteps {
    
    private Employee employee;
    private String startDate;
    private String endDate;
    private int totalWorkedHours;
    private List<WorkLog> workLogs = new ArrayList<>();
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

    @Then("the system should confirm that {int} hours were logged for the task {string} on {string}")
    public void the_system_should_confirm_that_hours_were_logged_for_the_task_on(int hours, String taskName, String date) {
        assertEquals(hours, totalWorkedHours, "The logged hours are incorrect for the task on the given date.");
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
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        workLog.setTaskId(taskId);
        workLog.setDate(date);
        return workLog;
    }
}
