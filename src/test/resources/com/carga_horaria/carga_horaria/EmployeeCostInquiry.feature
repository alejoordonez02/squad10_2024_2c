# US: https://github.com/alejoordonez02/squad10_2024_2c/issues/2

Feature: Consulta de costos de empleados

 Scenario: Successfully view total cost of hours worked by an employee
    Given I am a operations manager in the system
    And the employee "John Doe" has logged 160 hours in the month "2024-11-01"
    And "John Doe" has a salary of $20 per hour
    When I request the total cost of hours worked for "John Doe" in the month "2024-11-01"
    Then the system should display the total cost of hours worked for "John Doe" as $3,200

  Scenario: View total cost of hours worked by all employees in a period
    Given I am a operations manager in the system
    And employees have logged hours for the month "2024-11-01"
    When I request the total cost of hours worked by all employees in the period "2024-11-01"
    Then the system should display the total cost of hours worked for each employee
    And the total cost for all employees should be calculated based on their respective hourly rates and logged hours

  Scenario: View cost of worked hours by employee for a specific project
    Given I am a operations manager in the system
    And the employee "Jane Smith" has logged 40 hours for "Project A" in the month "2024-11-01"
    And "Jane Smith" has a salary of $25 per hour
    When I request the total cost of hours worked for "Jane Smith" on "Project A" for the month "2024-11-01"
    Then the system should display the total cost of hours worked by "Jane Smith" on "Project A" as $1,000

  Scenario: View total cost of hours worked by a team
    Given I am a operations manager in the system
    And multiple employees have logged hours for "Project B" in the month "2024-11-02"
    When I request the total cost of hours worked by the team for "Project B" in the month "2024-11-02"
    Then the system should display the total cost of hours worked by all employees in the team
    And the total cost should be calculated based on the sum of each employee's hours worked and their hourly rate

  Scenario: View total cost of worked hours by the team for a month
    Given I am a operations manager in the system
    And multiple employees have logged hours in the month "2024-11-03"
    When I request the total cost of hours worked by the team for the month "2024-11-03"
    Then the system should display the total cost of hours worked for the entire team
    And the cost should be calculated based on each employee's hourly rate and hours worked

  Scenario: View cost of worked hours with date range filter
    Given I am a operations manager in the system
    And the employee "John Doe" has logged 80 hours for "Project D" between "2024-11-01" and "2024-11-15"
    And "John Doe" has a salary of $20 per hour
    When I request the total cost of hours worked by "John Doe" for "Project D" between "2024-11-01" and "2024-11-15"
    Then the system should display the total cost of hours worked for "John Doe" as $1,600