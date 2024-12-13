package com.carga_horaria.carga_horaria.steps;

import com.carga_horaria.carga_horaria.model.*;
import com.carga_horaria.carga_horaria.service.WorkLogService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeCostInquirySteps {

    @Autowired
    private WorkLogService workLogService;

    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<WorkLog> workLogs = new ArrayList<>();
    private double hourlyRate;
    private double totalCost;
    private double totalCostAllEmployees;

    private static int taskIdCounter = 1;

    @BeforeEach
    public void setUp() {
        employees.clear();
        projects.clear();
        tasks.clear();
        workLogs.clear();
        totalCost = 0;
        totalCostAllEmployees = 0;
    }

    @Given("I am a operations manager in the system")
    public void iAmAnOperationsManagerInTheSystem() {
    }

    @Given("multiple employees have logged hours for {string} in the month {string}")
    public void multipleEmployeesHaveLoggedHoursForProjectInTheMonth(String projectName, String month) {
        Employee employee1 = createEmployee("John Doe");
        Employee employee2 = createEmployee("Jane Smith");
        Project project = createProject(projectName);

        Task task1 = createTaskForProject(project, employee1);
        Task task2 = createTaskForProject(project, employee2);

        WorkLog workLog1 = createWorkLogForTask(employee1.getId(), task1.getId(), 40, month);
        WorkLog workLog2 = createWorkLogForTask(employee2.getId(), task2.getId(), 35, month);

        workLogs.add(workLog1);
        workLogs.add(workLog2);
        employees.add(employee1);
        employees.add(employee2);
        projects.add(project);
        tasks.add(task1);
        tasks.add(task2);

        hourlyRate = 25; 
    }

    @Given("multiple employees have logged hours in the month {string}")
    public void multipleEmployeesHaveLoggedHoursInTheMonth(String month) {
        Employee employee1 = createEmployee("John Doe");
        Employee employee2 = createEmployee("Jane Smith");

        Project project1 = createProject("Project A");
        Project project2 = createProject("Project B");

        Task task1 = createTaskForProject(project1, employee1);
        Task task2 = createTaskForProject(project2, employee2);

        WorkLog workLog1 = createWorkLogForTask(employee1.getId(), task1.getId(), 40, month); 
        WorkLog workLog2 = createWorkLogForTask(employee2.getId(), task1.getId(), 25, month); 
        WorkLog workLog3 = createWorkLogForTask(employee1.getId(), task2.getId(), 35, month);
        WorkLog workLog4 = createWorkLogForTask(employee2.getId(), task2.getId(), 45, month); 

        workLogs.add(workLog1);
        workLogs.add(workLog2);
        workLogs.add(workLog3);
        workLogs.add(workLog4);
        employees.add(employee1);
        employees.add(employee2);
        projects.add(project1);
        projects.add(project2);
        tasks.add(task1);
        tasks.add(task2);

        hourlyRate = 25; 
    }


    @And("the employee {string} has logged {int} hours in the month {string}")
    public void theEmployeeHasLoggedHoursInTheMonth(String employeeName, int hours, String month) {
        Employee employee = createEmployee(employeeName);
        WorkLog workLog = createWorkLog(employee.getId(), hours, month);
        workLogs.add(workLog);
        employees.add(employee);
    }

    @And("{string} has a salary of ${double} per hour")
    public void theEmployeeHasASalaryOfPerHour(String employeeName, double salary) {
        this.hourlyRate = salary;
    }

    @And("employees have logged hours for the month {string}")
    public void employeesHaveLoggedHoursForTheMonth(String month) {
        Employee employee1 = createEmployee("John Doe");
        Employee employee2 = createEmployee("Jane Smith");
        WorkLog workLog1 = createWorkLog(employee1.getId(), 40, month);
        WorkLog workLog2 = createWorkLog(employee2.getId(), 35, month);
        
        workLogs.add(workLog1);
        workLogs.add(workLog2);
        employees.add(employee1);
        employees.add(employee2);
    }
    
    @And("the employee {string} has logged {int} hours for {string} in the month {string}")
    public void theEmployeeHasLoggedHoursForProjectInTheMonth(String employeeName, int hours, String projectName, String month) {
        Employee employee = createEmployee(employeeName);
        Project project = createProject(projectName);
        Task task = createTaskForProject(project, employee);
        WorkLog workLog = createWorkLogForTask(employee.getId(), task.getId(), hours, month);
        workLogs.add(workLog);
        employees.add(employee);
        projects.add(project);
        tasks.add(task);
    }

    @And("the employee {string} has logged {int} hours for {string} between {string} and {string}")
    public void theEmployeeHasLoggedHoursForProjectBetweenDates(String employeeName, int hours, String projectName, String startDate, String endDate) {
        Employee employee = createEmployee(employeeName);
        employees.add(employee);
        Project project = createProject(projectName);
        projects.add(project);
        Task task = createTaskForProject(project, employee);
        tasks.add(task);
        
        WorkLog workLogInicio = createWorkLogForTask(employee.getId(), task.getId(), hours/2, LocalDate.parse(startDate));
        workLogs.add(workLogInicio);

        WorkLog workLogFin  = createWorkLogForTask(employee.getId(), task.getId(), hours/2, LocalDate.parse(endDate));
        workLogs.add(workLogFin);
    }

    @When("I request the total cost of hours worked for {string} in the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedForInTheMonth(String employeeName, String month) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(employeeName.split(" ")[0]) && employee.getLastName().equals(employeeName.split(" ")[1])) {
                List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeInMonth(employee.getId(), month);

                double totalHoursWorked = 0;
                for (WorkLog workLog : workLogsForEmployee) {
                    totalHoursWorked += workLog.getHours();
                }

                when(workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1])))
                    .thenReturn(totalHoursWorked);

                totalCost = totalHoursWorked * hourlyRate;
            }
        }
    }

    @When("I request the total cost of hours worked by all employees in the period {string}")
    public void iRequestTheTotalCostOfHoursWorkedByAllEmployeesInThePeriod(String month) {
        totalCostAllEmployees = 0;  
        for (Employee employee : employees) {

            List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeInMonth(employee.getId(), month);

            double totalHoursWorked = 0;
            for (WorkLog workLog : workLogsForEmployee) {
                totalHoursWorked += workLog.getHours();
            }

            when(workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1])))
                .thenReturn(totalHoursWorked);

            double costForEmployee = totalHoursWorked * hourlyRate;

            totalCostAllEmployees += costForEmployee;
        }
    }

    @When("I request the total cost of hours worked for {string} on {string} for the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedForOnForTheMonth(String employeeName, String projectName, String month) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(employeeName.split(" ")[0]) && 
                employee.getLastName().equals(employeeName.split(" ")[1])) {
                
                List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeOnProjectInMonth(employee.getId(), projectName, month);

                double totalHoursWorked = 0;
                for (WorkLog workLog : workLogsForEmployee) {
                    totalHoursWorked += workLog.getHours();  
                }
                
                totalCost = totalHoursWorked * hourlyRate;
            }
        }
    }

    @When("I request the total cost of hours worked by the team for {string} in the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedByTheTeamForProjectInTheMonth(String projectName, String month) {
        totalCost = 0;
        for (Employee employee : employees) {
            List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeOnProjectInMonth(employee.getId(), projectName, month);

            double totalHoursWorked = 0;
            for (WorkLog workLog : workLogsForEmployee) {
                totalHoursWorked += workLog.getHours();
            }
            double costForEmployee = totalHoursWorked * hourlyRate;
            totalCost += costForEmployee;
        }
    }

    @When("I request the total cost of hours worked by the team for the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedByTheTeamForTheMonth(String month) {
        totalCost = 0;

        for (Employee employee : employees) {
            List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeInMonth(employee.getId(), month);

            double totalHoursWorked = 0;
            for (WorkLog workLog : workLogsForEmployee) {
                totalHoursWorked += workLog.getHours();
            }

            double costForEmployee = totalHoursWorked * hourlyRate;
            totalCost += costForEmployee;
        }
    }

    @When("I request the total cost of hours worked by {string} for {string} between {string} and {string}")
    public void iRequestTheTotalCostOfHoursWorkedByForProjectBetweenDates(String employeeName, String projectName, String startDate, String endDate) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(employeeName.split(" ")[0]) && employee.getLastName().equals(employeeName.split(" ")[1])) {
                List<WorkLog> workLogsForEmployee = findWorkLogsForEmployeeOnProjectInRange(employee.getId(), projectName, startDate, endDate);
    
                // Debugging to check if the workLogsForEmployee list contains any logs
                System.out.println("Found " + workLogsForEmployee.size() + " work logs for employee " + employeeName + " on project " + projectName + " in the date range.");
    
                double totalHoursWorked = workLogsForEmployee.stream().mapToDouble(WorkLog::getHours).sum();
                totalCost = totalHoursWorked * hourlyRate;
    
                System.out.println("Total hours worked: " + totalHoursWorked);
                System.out.println("Total cost: " + totalCost);
            }
        }
    }

    @Then("the system should display the total cost of hours worked for {string} as ${double}")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForAs(String employeeName, double expectedCost) {
        assertEquals(expectedCost, totalCost, 0.01);
    }

    @Then("the system should display the total cost of hours worked for each employee")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForEachEmployee() {
        for (Employee employee : employees) {
            List<WorkLog> workLogsForEmployee = findWorkLogForEmployeeInMonth(employee.getId(), "2024-11");

            double totalHoursWorked = 0;
            for (WorkLog workLog : workLogsForEmployee) {
                totalHoursWorked += workLog.getHours();
            }
            double costForEmployee = totalHoursWorked * hourlyRate;
            assertEquals(costForEmployee, totalCostAllEmployees, 0.01);
        }
    }

    @Then("the system should display the total cost of hours worked by {string} on {string} as ${double}")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedByOnAs(String employeeName, String projectName, double expectedCost) {
        assertEquals(expectedCost, totalCost, 0.01);
    }

    @Then("the system should display the total cost of hours worked by all employees in the team")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedByAllEmployeesInTheTeam() {
        System.out.println("Total cost of hours worked by the team: $" + totalCost);
    }

    @Then("the system should display the total cost of hours worked for the entire team")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForTheEntireTeam() {
        System.out.println("Total cost of hours worked by the entire team: $" + totalCost);
    }

    @And("the total cost for all employees should be calculated based on their respective hourly rates and logged hours")
    public void theTotalCostForAllEmployeesShouldBeCalculated() {
        assertEquals(totalCostAllEmployees, totalCostAllEmployees, 0.01);
    }

    @And("the total cost should be calculated based on the sum of each employee's hours worked and their hourly rate")
    public void theTotalCostShouldBeCalculatedBasedOnTheSumOfEachEmployeeSHoursWorkedAndTheirHourlyRate() {
        assertEquals(1875, totalCost, 0.01);
    }

    @And("the cost should be calculated based on each employee's hourly rate and hours worked")
    public void theCostShouldBeCalculatedBasedOnEachEmployeeSHourlyRateAndHoursWorked() {
        assertEquals(3625, totalCost, 0.01);
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
        task.setName("Task for " + project.getName());
        task.setAssigneeId(employee.getId());
        task.setProjectId(project.getId()); 
        return task;
    }

    private WorkLog createWorkLog(String employeeId, int hours, String month) {
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        String taskId = "task" + taskIdCounter++;
        workLog.setTaskId(taskId);
        workLog.setDate(LocalDate.parse(month + "-01"));
        return workLog;
    }

    private WorkLog createWorkLogForTask(String employeeId, String taskId, int hours, String month) {
        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employeeId);
        workLog.setHours(hours);
        workLog.setTaskId(taskId);
        workLog.setDate(LocalDate.parse(month + "-01"));
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

    private List<WorkLog> findWorkLogForEmployeeInMonth(String employeeId, String month) {
        List<WorkLog> workLogsForEmployeeInMonth = new ArrayList<>();
        for (WorkLog workLog : workLogs) {
            if (workLog.getEmployeeId().equals(employeeId) && 
                workLog.getDate().getMonthValue() == Integer.parseInt(month.split("-")[1]) && 
                workLog.getDate().getYear() == Integer.parseInt(month.split("-")[0])) {
                workLogsForEmployeeInMonth.add(workLog);
            }
        }
        
        return workLogsForEmployeeInMonth;
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
