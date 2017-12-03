Feature: This feature should check invalid login

  Scenario Outline: Login with improper credentials
    Given login form
    When we login with invalid credentials (<email> and <pass>)
    Then we should see error message

     Examples:
          |        email          |    pass   |
          |  "someEmail@abc.eu"   |  "secret" |
          |  "anotherOne@com.pl"  |  "secret" |
