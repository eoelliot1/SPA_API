Feature: Student
  As a Student I want to be able to see all courses that are available to enrol and unenroll from courses
  So that I can manage my profile

  Background:
    Given I am logged in as a Student

  @Happy
  Scenario: There are courses in the system
    When I click on my course
    Then I should see a the course that i am enrolled in and what other courses available

  @Happy
  Scenario: Change to different course
    When I click on my profile
    Then I should be able to edit to change to the course I want
    Then I should be redirected to the student dashboard

  @Happy
  Scenario: Change my name
    When I click on my profile
    Then I should be able to edit my name
    Then I should be redirected to the student dashboard

  @Happy
  Scenario: Student tries to unenroll from a course they are enrolled in
    When I unenroll from course
    Then I should see a student error message
