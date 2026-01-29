#Feature: Course Management
#  As a trainer
#  I want to manage courses (view, search, add, edit, and delete)
#  So that I can effectively organize and maintain them
#
#  Background:
#    Given I am logged in as a Trainer
#    And I navigate to the courses page
#
#  Scenario: View all available courses
#    When I navigate to the courses page
#    Then I should see all available courses listed
#
#  Scenario: Search for an existing course
#    When I search for a course named "Data"
#    Then I should see course "Data" listed
#
#  Scenario: Search for a course that does not exist
#    When I search for a course named "Python"
#    Then I should see no courses listed
#
#  # Editing Course Scenarios
#  Scenario: Edit an existing course
#    Given there is an existing course named "Data"
#    When I click "Edit" for course "Data"
#    And I update course name to "Advanced Data Analysis"
#    And I click "Update"
#    Then I should see "Advanced Data Analysis" in the courses list
#    And I should not see "Data" in the courses list
#
#  Scenario: Cancel course editing
#    Given there is an existing course named "Software Testing"
#    When I click "Edit" for course "Software Testing"
#    And I update course name to "Full Stack Development"
#    And I click "Cancel"
#    Then I should see "Software Testing" in the courses list
#    And I should not see "Full Stack Development" in the courses list
#
#  # Deleting Course Scenarios
#  Scenario: Delete an existing course
#    Given there is an existing course named "Software Testing"
#    When I click "Delete" for course "Mobile App Development"
#    And I confirm the deletion
#    Then I should not see "Software Testing" in the courses list
#
#  Scenario: Cancel course deletion
#    Given there is an existing course named "Advanced Data Analysis"
#    When I click "Delete" for course "Advanced Data Analysis"
#    And I cancel the deletion confirmation
#    Then I should still see "Advanced Data Analysis" in the courses list
#
#  # Viewing Course Details
#  Scenario: View course details
#    Given there is an existing course named "Advanced Data Analysis"
#    When I click "view" on course "Advanced Data Analysis"
#    Then I should see the course details page
#    And I should see "Advanced Data Analysis" as the course name
#    And I should see the course id displayed
#
#
##      # Adding Course Scenarios
##  Scenario: Add a new course successfully
##    When I click "Add New Course"
##    And I enter course name as "Advanced JavaScript"
##    And I enter course date as "2024-12-15"
##    And I click "Save"
##    Then I should see a success message "Course added successfully"
##    And I should see "Advanced JavaScript" in the courses list
##    And I should see "2024-12-15" displayed for "Advanced JavaScript"