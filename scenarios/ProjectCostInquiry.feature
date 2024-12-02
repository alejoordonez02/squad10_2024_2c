# US: https://github.com/alejoordonez02/squad10_2024_2c/issues/4

Feature: Consulta de costos por proyecto

 Scenario: Successfully view the monthly cost report of a project
    Given I am a finance manager in the system
    And I have access to the projects and their employees
    When I request the monthly cost report for the project "Project A" for the month "2024-11"
    Then the system should display the total cost for the project "Project A" for the month "2024-11"
    And the report should include the breakdown of costs per employee, based on their hours worked and salaries

  Scenario: View cost report breakdown by employee
    Given I am a finance manager in the system
    And employees have logged hours worked for the project "Project B"
    When I request the monthly cost report for the project "Project B" for the month "2024-11"
    Then the system should show a breakdown of costs by employee
    And each employee's cost should be calculated based on their worked hours and salary

  Scenario: View accumulated costs for a project
    Given I am a finance manager in the system
    And multiple employees have logged hours for the project "Project C"
    When I request the monthly cost report for the project "Project C" for the month "2024-11"
    Then the system should show the total accumulated cost for the project, based on the sum of employee costs
    And the total cost should reflect the total hours worked by all employees for that month

  Scenario: Filter monthly cost report by date range
    Given I am a finance manager in the system
    When I request the monthly cost report for the project "Project D" for the date range "2024-11-01" to "2024-11-30"
    Then the system should show the cost report for the selected date range
    And the report should include only hours worked within that period

  Scenario: Error when employee salary data is missing
    Given I am a finance manager in the system
    And some employees have missing salary information
    When I request the monthly cost report for "Project G"
    Then the system should show an error message saying "Some employee salary data is missing"
    And the report should not include the costs for those employees
