# US: https://github.com/alejoordonez02/squad10_2024_2c/issues/3

Feature: Consulta de horas trabajadas de empleados

Scenario: Successfully view hours worked by an employee in a time period
    Given I am a operations manager in the system
    When I request the worked hours for the employee "John Doe" from "2024-11-01" to "2024-11-30"
    Then the system should display the total hours worked by "John Doe" for the period "2024-11-01" to "2024-11-30"

  Scenario: Filter worked hours by employee and project
    Given I am a operations manager in the system
    When I request the worked hours for the employee "Jane Smith" for the project "Project A" from "2024-11-01" to "2024-11-30"
    Then the system should display the total hours worked by "Jane Smith" on "Project A" in the period "2024-11-01" to "2024-11-30"

  Scenario: View worked hours for an employee with no hours worked
    Given I am a operations manager in the system
    And the employee "Emily White" has not logged any hours in the period "2024-11-01" to "2024-11-30"
    When I request the worked hours for "Emily White" for the period "2024-11-01" to "2024-11-30"
    Then the system should display "0 hours worked" for "Emily White" in the period "2024-11-01" to "2024-11-30"

  Scenario: Attempt to request worked hours with incorrect date format
    Given I am a operations manager in the system
    When I try to request worked hours for the employee "John Doe" with an incorrect date format "01-13-2024" to "2024-11-30"
    Then the system should display an error message saying "Invalid date format"