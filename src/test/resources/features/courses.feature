Feature: Browse courses
  As a user
  I want to view and search available courses
  So that I can find relevant courses

  Background:
    Given I am logged in as a Trainer
    And I am on the courses page

  Scenario: Search for an existing course
    When I search for a course named "Data"
    Then I should see course "Data" listed

  Scenario: Search for a course that does not exist
    When I search for a course named "Python"
    Then I should see no courses listed
