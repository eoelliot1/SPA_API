Feature: Trainer Course Actions
  As a Trainer
  I want to search and manage my course assignments
  So that I can manage my teaching workload

  # Happy Path - Search courses by name
  Scenario: Trainer searches for a course by name
    Given I am logged in as a Trainer and there are courses in the system
    When I search for a course named "Java Basics" on the "Courses" page
    Then I should see the course "Java Basics" displayed in the search results

  # Sad Path - Search yields no results
  Scenario: Trainer searches for a course that does not exist
    Given I am logged in as a Trainer and there are courses in the system
    When I search for a course named "Python Advanced" on the "Courses" page
    Then I should see a message indicating that no courses match the search

  # Happy Path - Remove myself from a course
  Scenario: Trainer removes assignment from a course
    Given I am logged in as a Trainer and I am assigned to the course "Spring Boot"
    When I choose to remove myself from "Spring Boot" on the "Courses" page
    Then I should see a confirmation that I am no longer assigned to the course

  # Sad Path - Remove assignment when not assigned
  Scenario: Trainer tries to remove assignment from a course they are not assigned to
    Given I am logged in as a Trainer and I am not assigned to the course "Angular Basics"
    When I attempt to remove myself from "Angular Basics" on the "Courses" page
    Then I should see an error message indicating I am not assigned to that course
