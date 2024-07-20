Feature: Amazon Login
  Scenario: Successful login to Amazon
    Given I am on the Amazon login page
    When I enter a valid username and password
    And I click on the login button
    Then I should be redirected to the homepage
