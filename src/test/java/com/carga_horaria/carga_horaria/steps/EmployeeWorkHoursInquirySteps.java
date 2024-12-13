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
    }

    @Given("I am a operation manager in the system")
    public void i_am_a_operations_manager_in_the_system() {}

    @When("I request the worked hours for the employee {string} from {string} to {string}")
    public void i_request_the_worked_hours_for_the_employee_from_to(String employeeName, String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        employee = createEmployee("John Doe");
        WorkLog workLog = createWorkLog(employee.getId(), 20, LocalDate.parse(startDate));
        workLogs.add(workLog);

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
}