

Feature: Trainer Management
  As a trainer
  I want to manage trainers (view, search, add, and edit)
  So that I can maintain trainer information effectively

  Background:
    Given I am on the login page
    When I am logged in as a Trainer
    Then I should be redirected to the trainer dashboard

  # =========================
  # Viewing Trainers
  # =========================

  Scenario: View all available trainers
       Then I should see all available trainers listed


  # =========================
  # Searching Trainers
  # =========================

  Scenario: Search trainers by course duration
    When I search for courses longer than "120" days
    Then I should see trainers assigned to long courses
#
  Scenario: Search trainers with no matching course duration
    When I search for courses longer than "1000" days
    Then I should see no trainers listed

  # =========================
  # Adding Trainer
  # =========================

  Scenario: Navigate to add new trainer page
    When I click "Add New Trainer"
    Then I should be on the add trainer page

  Scenario: Add a new trainer successfully
    When I click Add New Trainer
    And I enter trainer name "Philip Windridge"
    And I select course "Data"
    And I click Save Trainer
    Then I should see trainer "Philip Windridge" listed

  # =========================
  # Editing Trainer
  # =========================

  Scenario: Edit an existing trainer
    Given there is an existing trainer named "Sarah Coach"
    When I click "Edit" for trainer "Sarah Coach"
    And I update trainer name to "Sarah Senior Trainer"
    And I change course to "Software Testing"
    And I click Update Trainer
    #Then I should be redirected to the trainers page
    And I should see course "Software Testing" assigned
#
#  Scenario: Cancel trainer editing
#    Given there is an existing trainer named "Sarah Coach"
#    When I click "Edit" for trainer "Sarah Coach"
#    And I update trainer name to "Sarah Lead Coach"
#    And I click "Cancel"
#    Then I should see trainer "Sarah Coach" listed
#    And I should not see "Sarah Lead Coach"

  # =========================
  # Trainer Details
  # =========================

#  Scenario: View trainer details
#    Given there is an existing trainer named "Mike Mentor"
#    When I click "View" on trainer "Mike Mentor"
#    Then I should see the trainer details page
#    And I should see "Mike Mentor" as the trainer name
#    And I should see the trainer id displayed
#    And I should see the assigned course displayed






