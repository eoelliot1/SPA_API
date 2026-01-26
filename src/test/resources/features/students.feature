Feature: Student Course Actions
  As a Student
  I want to search, enrol, and unenrol from courses
  So that I can manage my learning

  # Happy Path - Search courses by name
  Scenario: Student searches for a course by name
    Given I am logged in as a Student and there are courses in the system
    When I search for a course named "Java Basics" on the "Courses" page
    Then I should see the course "Java Basics" displayed in the search results

  # Sad Path - Search yields no results
  Scenario: Student searches for a course that does not exist
    Given I am logged in as a Student and there are courses in the system
    When I search for a course named "Python Advanced" on the "Courses" page
    Then I should see a message indicating that no courses match the search

  # Happy Path - Unenrol from a course
  Scenario: Student unenrols from a course
    Given I am logged in as a Student and I am enrolled in the course "Spring Boot"
    When I choose to unenrol from "Spring Boot" on the "Courses" page
    Then I should see a confirmation that I am no longer enrolled in the course

  # Sad Path - Unenrol from a course not enrolled in
  Scenario: Student tries to unenrol from a course they are not enrolled in
    Given I am logged in as a Student and I am not yet enrolled in the course "Angular Basics"
    When I attempt to unenrol from "Angular Basics" on the "Courses" page
    Then I should see an error message indicating I am not enrolled in that course
