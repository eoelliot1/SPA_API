Feature: Course Management
  As a trainer
  I want to manage courses (view, search, add, edit, and delete)
  So that I can effectively organize and maintain them

  Background:
    Given I am on the login page
    When I am logged in as a Trainer
    Then I should be redirected to the trainer dashboard

  Scenario: View all available courses
    When I open the courses page
    Then I should see a list of all available courses

  Scenario Outline: Search for an existing course
    When I open the courses page
    And I search for a course named "<course>"
    Then I should see the course listed

    Examples:
      | course |
      | Data   |

  Scenario Outline: Search for a course that does not exist
    When I open the courses page
    And I search for a course named "<course>"
    Then I should see no courses listed

    Examples:
      | course  |
      | Python  |

  # Editing Course Scenarios
  Scenario: Cancel course editing
    Given I open the courses page
    When I click Edit for course "Data"
    And I am on update course page
    And I attempt to update course name to "Full Stack Development"
    And I click Cancel
    Then I should see "Data" in the courses list

  Scenario: Edit an existing course
    Given I open the courses page
    When I click Edit for course "Data"
    And I am on update course page
    And I update course name to "Advanced Data Analysis"
    Then I should see "Advanced Data Analysis" in the courses list

  # Deleting Course Scenarios
  Scenario: Cancel course deletion
    Given I open the courses page
    When I click Delete for course "DevOps"
    And I cancel the deletion confirmation
    Then I should see "DevOps" in the courses list

  Scenario: Delete an existing course
    Given I open the courses page
    When I click Delete for course "DevOps"
    And I confirm the deletion
    Then I should not see "DevOps" in the courses list


#  # Viewing Course Details
#  Scenario: View course details
#    Given there is an existing course named "Advanced Data Analysis"
#    When I click "view" on course "Advanced Data Analysis"
#    Then I should see the course details page
#    And I should see "Advanced Data Analysis" as the course name
#    And I should see the course id displayed

