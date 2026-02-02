Feature: Student
  As a Student I want to be able to see all courses that are available and I can edit my profile

  Background:
    Given I am logged in as a Student

  @Happy
  Scenario: There are courses in the system
    When I click on my course
    Then I should see a the course that i am enrolled in and what other courses available


  @Happy
  Scenario: Change my name
    When I click on my profile
    Then I should be able to edit my name
    Then I should be redirected to the student dashboard

