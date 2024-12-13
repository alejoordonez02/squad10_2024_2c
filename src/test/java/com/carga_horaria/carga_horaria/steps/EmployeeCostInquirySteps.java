package com.carga_horaria.carga_horaria.steps;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.service.WorkLogService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;

public class EmployeeCostInquirySteps {

    @Autowired
    private WorkLogService workLogService;

    private Employee employee;
    private double hourlyRate;
    private double totalCost;
    private WorkLog workLog;

    @BeforeEach
    public void setUp() {
    }

    @Given("I am a operations manager in the system")
    public void iAmAnOperationsManagerInTheSystem() {
    }

    @And("the employee {string} has logged {int} hours in the month {string}")
    public void theEmployeeHasLoggedHoursInTheMonth(String employeeName, int hours, String month) {
        this.employee = new Employee();
        employee.setFirstName(employeeName.split(" ")[0]);
        employee.setLastName(employeeName.split(" ")[1]);
        employee.setId("12345");

        workLog = new WorkLog();
        workLog.setEmployeeId(employee.getId());
        workLog.setHours(hours);
        workLog.setTaskId("task123");
        workLog.setDate(LocalDate.parse(month + "-01"));

        when(workLogService.addWorkLog(workLog)).thenReturn(workLog);
    }

    @And("{string} has a salary of ${double} per hour")
    public void theEmployeeHasASalaryOfPerHour(String employeeName, double salary) {
        this.hourlyRate = salary;
    }

    @When("I request the total cost of hours worked for {string} in the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedForInTheMonth(String employeeName, String month) {
        double hours = workLog.getHours();
        when(workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1])))
            .thenReturn((double) hours); 

        double totalHours = workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1]));
        this.totalCost = totalHours * hourlyRate;
    }

    @Then("the system should display the total cost of hours worked for {string} as ${double}")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForAs(String employeeName, double expectedCost) {
        assertEquals(expectedCost, totalCost, 0.01);
    }
}
