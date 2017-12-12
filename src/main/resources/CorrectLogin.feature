Feature: This feature should check correct login procedure

  Scenario: Login with correct credentials
    Given login page form
    When we login with valid credentials
    Then we should see account page