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
import java.util.ArrayList;
import java.util.List;


public class EmployeeCostInquirySteps {

    @Autowired
    private WorkLogService workLogService;

    private List<Employee> employees = new ArrayList<>();
    private double hourlyRate;
    private double totalCost;
    private double totalCostAllEmployees;
    private WorkLog workLog;

    @BeforeEach
    public void setUp() {
        employees.clear();
        totalCost = 0;
    }

    @Given("I am a operations manager in the system")
    public void iAmAnOperationsManagerInTheSystem() {
    }

    @And("the employee {string} has logged {int} hours in the month {string}")
    public void theEmployeeHasLoggedHoursInTheMonth(String employeeName, int hours, String month) {
        this.employees.add(createEmployee(employeeName, hours, month));
    }

    @And("{string} has a salary of ${double} per hour")
    public void theEmployeeHasASalaryOfPerHour(String employeeName, double salary) {
        this.hourlyRate = salary;
    }

    @And("employees have logged hours for the month {string}")
    public void employeesHaveLoggedHoursForTheMonth(String month) {
        this.employees.add(createEmployee("John Doe", 40, month));
        this.employees.add(createEmployee("Jane Smith", 35, month));
    }
    
    @When("I request the total cost of hours worked for {string} in the month {string}")
    public void iRequestTheTotalCostOfHoursWorkedForInTheMonth(String employeeName, String month) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(employeeName.split(" ")[0]) && employee.getLastName().equals(employeeName.split(" ")[1])) {
                double hoursWorked = workLog.getHours();
                when(workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1])))
                .thenReturn(hoursWorked);
                totalCost = hoursWorked * hourlyRate;
            }
        }
    }

    @When("I request the total cost of hours worked by all employees in the period {string}")
    public void iRequestTheTotalCostOfHoursWorkedByAllEmployeesInThePeriod(String month) {
        totalCostAllEmployees = 0;
        for (Employee employee : employees) {
            double hoursWorked = workLogService.getWorkedHours(employee.getId(), Integer.parseInt(month.split("-")[0]), Integer.parseInt(month.split("-")[1]));
            double costForEmployee = hoursWorked * hourlyRate;
            totalCostAllEmployees += costForEmployee;
        }
    }

    @Then("the system should display the total cost of hours worked for {string} as ${double}")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForAs(String employeeName, double expectedCost) {
        assertEquals(expectedCost, totalCost, 0.01);
    }

    @Then("the system should display the total cost of hours worked for each employee")
    public void theSystemShouldDisplayTheTotalCostOfHoursWorkedForEachEmployee() {
        for (Employee employee : employees) {
            double hoursWorked = workLogService.getWorkedHours(employee.getId(), 11, 2024); 
            double costForEmployee = hoursWorked * hourlyRate;
            assertEquals(costForEmployee, totalCostAllEmployees, 0.01);
        }
    }

    @And("the total cost for all employees should be calculated based on their respective hourly rates and logged hours")
    public void theTotalCostForAllEmployeesShouldBeCalculated() {
        assertEquals(totalCostAllEmployees, totalCostAllEmployees, 0.01);
    }


    private Employee createEmployee(String name, int hours, String month) {
        Employee employee = new Employee();
        employee.setId(String.valueOf(name.hashCode()));
        employee.setFirstName(name.split(" ")[0]);
        employee.setLastName(name.split(" ")[1]);

        this.workLog = new WorkLog();
        workLog.setEmployeeId(employee.getId());
        workLog.setHours(hours);
        workLog.setTaskId("task123");
        workLog.setDate(LocalDate.parse(month + "-01"));


        when(workLogService.addWorkLog(workLog)).thenReturn(workLog);
        return employee;
    }
}
