package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Employee;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.service.EmployeeService;
import com.carga_horaria.carga_horaria.service.WorkLogService;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EmployeeCostInquirySteps {

    private WorkLogService workLogService;

    private Employee employee;
    private double hourlyRate;
    private double totalCost;

    public EmployeeCostInquirySteps(EmployeeService employeeService, WorkLogService workLogService) {
        this.workLogService = workLogService;
    }

    @Given("I am an operations manager in the system")
    public void iAmAnOperationsManagerInTheSystem() {
    }

    @And("the employee {string} has logged {int} hours in the month {string}")
    public void theEmployeeHasLoggedHoursInTheMonth(String employeeName, int hours, String month) {
        this.employee = new Employee();
        employee.setFirstName(employeeName.split(" ")[0]);
        employee.setLastName(employeeName.split(" ")[1]);
        employee.setId("12345");

        WorkLog workLog = new WorkLog();
        workLog.setEmployeeId(employee.getId());
        workLog.setHours(hours);
        workLog.setTaskId("task123");
        workLog.setDate(LocalDate.parse(month + "-01"));
        workLogService.addWorkLog(workLog);
    }

    @And("{string} has a salary of ${double} per hour")
    public void theEmployeeHasASalaryOfPerHour(String employeeName, double salary) {
        this.hourlyRate = salary;
    }

    @When("I request the total cost of hours worked for {string} in the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedForInTheMonth(String employeeName, String month) {
        double totalHours = workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1]));
        this.totalCost = totalHours * hourlyRate;
    }

    @Then("the system should display the total cost of hours worked for {string} as ${double}")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForAs(String employeeName, double expectedCost) {
        assertEquals(expectedCost, totalCost, 0.01);
    }
}
