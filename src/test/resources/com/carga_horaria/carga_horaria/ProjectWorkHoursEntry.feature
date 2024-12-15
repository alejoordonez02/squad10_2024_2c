# US: https://github.com/alejoordonez02/squad10_2024_2c/issues/1

Feature: Registro de horas por proyecto

 Scenario: Successfully register worked hours
    Given I have a project called "API Development" and tasks assigned
    When I log 5 hours worked for the task "API Development" on "2024-12-01"
    Then the system should confirm that 5 hours were logged for the task "API Development" on "2024-12-01"
    And the total worked hours for the project should be updated

 Scenario: Attempt to log hours without specifying a task
    Given I have a project but I not have any tasks assigned
    When I try to log 4 hours worked without specifying a task
    Then the system should show an error message saying "Task is required"
    And the hours should not be logged

  Scenario: Attempt to log hours with incorrect date format
    Given I have a project called "API Development" and tasks assigned
    When I try to log 6 hours worked for the task "Security Implementation" on "01-13-2024"
    Then the system should show an error message saying "Incorrect date format"
    And the hours should not be logged

  Scenario: Update previously logged hours
    Given I have a project called "API Development" and tasks assigned
    And I have already logged 3 hours worked for the task "API Development" on "2024-12-01"
    When I update the log to 6 hours for the task "API Development" on "2024-12-01"
    Then the system should update the logged hours for that task and date to 6 hours
    And the total worked hours should reflect the change

  Scenario: View logged hours for a specific date
    Given I have a project called "API Development" and tasks assigned
    And I have logged 4 hours worked for the task "API Development" on "2024-12-01"
    When I access the logged hours for "2024-12-01"
    Then the system should show 4 hours worked for the task "API Development" on that date

  Scenario: Attempt to log hours for a task not assigned
    Given I have a project but I not have any tasks assigned
    When I try to log 2 hours worked for the task "Tool Research" on "2024-12-01"
    Then the system should show an error message saying "Task not found"
    And the hours should not be logged

  Scenario: Log hours for multiple tasks on the same day
    Given I have a project called "API Development" and more than one tasks assigned
    When I log 4 hours for the task "API Development" on "2024-12-01"
    And I then log 2 hours for the task "Code Review" on the same day
    Then the system should log 4 hours for "API Development" and 2 hours for "Code Review" on "2024-12-01"
    And the total worked hours for the day should be 6

  Scenario: View a summary of logged hours for a project
    Given I have a project called "API Development" and tasks assigned
    And I have logged multiple hours for different tasks within the project "Project A"
    When I access the summary of logged hours for the project "Project A"
    Then the system should display the total hours worked for the project
    And it should break down the hours by each task and date