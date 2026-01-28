#Feature: Student course management
#  As a Student
#  I want to enrol and unenrol from courses
#  So that I can manage my learning
#
#  Scenario: Student enrols in a course
#    Given I am logged in as a Student
#    When I enrol in course "Data"
#    Then I should see a student confirmation message
#
#  Scenario: Student unenrols from a course
#    Given I am logged in as a Student
#    And I am enrolled in course "Software Testing" as a Student
#    When I unenrol from course "Software Testing"
#    Then I should see a student confirmation message
#
#  Scenario: Student tries to unenrol from a course they are not enrolled in
#    Given I am logged in as a Student
#    When I unenrol from course "Python"
#    Then I should see a student error message
