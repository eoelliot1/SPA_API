Feature: User Authentication
  As a registered user
  I want to log into the application
  So that I can manage my courses

  Scenario Outline: Successful login with valid credentials as a trainer
    Given I am on the login page
    When I enter email "<email>" and password "<password>"
    Then I should be redirected to the trainer dashboard

    Examples:
      | email              | password |
      | 4                  | trainerpass |


  Scenario Outline: Successful login with valid credentials as a trainee
    Given I am on the login page
    When I enter email "<email>" and password "<password>"
    Then I should be redirected to the trainee dashboard

    Examples:
      | email              | password |
      | 4                  | trainerpass |


  Scenario Outline: Unsuccessful login
    Given I am on the login page
    When I enter email "<wrong_email>" and password "<wrong_password>"
    Then I should remain on the login page


    Examples:
      | wrong_email              | wrong_password |
      | 2                        | wrongpass |