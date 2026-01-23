Feature: Courses Management
  As a user
  I want to view and enrol in courses
  So that I can manage my learning or teaching

  # Trainer - View all courses
  Scenario: Trainer views all courses
    Given I am logged in as a Trainer and there are courses in the system
    When I navigate to the "Courses" page
    Then I should see a list of all courses displayed on the page

  # Trainer - No courses available
  Scenario: Trainer views courses when none exist
    Given I am logged in as a Trainer and there are no courses in the system
    When I navigate to the "Courses" page
    Then I should see a message indicating that no courses are available

  # Student - View all courses
  Scenario: Student views all courses
    Given I am logged in as a Student and there are courses in the system
    When I navigate to the "Courses" page
    Then I should see a list of all courses displayed on the page

  # Student - No courses available
  Scenario: Student views courses when none exist
    Given I am logged in as a Student and there are no courses in the system
    When I navigate to the "Courses" page
    Then I should see a message indicating that no courses are available
