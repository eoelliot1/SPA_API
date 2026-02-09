

Feature: Trainer Management
  As a trainer
  I want to manage trainers (view, search, add, and edit)
  So that I can maintain trainer information effectively

  Background:
    Given I am on the login page
    When I am logged in as a Trainer
    Then I should be redirected to the trainer dashboard


  Scenario: View all available trainers
       Then I should see all available trainers listed



  Scenario: Search trainers by course duration
    When I search for courses longer than "120" days
    Then I should see trainers assigned to long courses

  Scenario: Search trainers with no matching course duration
    When I search for courses longer than "1000" days
    Then I should see no trainers listed


  Scenario: Navigate to add new trainer page
    When I click "Add New Trainer"
    Then I should be on the add trainer page

  Scenario: Add a new trainer successfully
    When I click Add New Trainer
    And I enter trainer name "Philip Windridge"
    And I select course "Data"
    And I click Save Trainer
    Then I should see trainer "Philip Windridge" listed


  Scenario: View trainer details
    Given I am on the trainers page
    And there is an existing trainer named "Sarah Coach"
    When I click "View Trainer" for trainer "Sarah Coach"
    Then I should be redirected to the view trainer page
    And I should see trainer name "Sarah Coach"


  Scenario: Navigate back to trainers list from trainer details
    Given I am on the trainers page
    And there is an existing trainer named "Sarah Coach"
    When I click "View Trainer" for trainer "Sarah Coach"
    Then I should be redirected to the view trainer page
    And I should see trainer name "Sarah Coach"
    When I click on the "Back to list" button
    Then I should see the trainers list









