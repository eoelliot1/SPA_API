Feature: Student
  As a Student I want to be able to see all courses that are available and I can edit my profile

  Background:
    Given I am on the login page
    When I am logged in as a Student
    Then I should be redirected to the Student dashboard

  @Happy
  Scenario: There are courses in the system
    When I click on my course
    Then I should see a the course that I am enrolled in and what other courses available


  @Happy
  Scenario: Change my name
    When I click on my profile
    Then I should be able to edit my name
    Then I should be redirected to the student profile

  @Happy
  Scenario:Cancel editing my name
    When I am updating my name and click cancel
    Then I should be redirected to my profile


  @Happy
    Scenario: Go back to Dashboard
      When I am on my profile page click back to dashboard
      Then I should be redirected to the student dashboard

