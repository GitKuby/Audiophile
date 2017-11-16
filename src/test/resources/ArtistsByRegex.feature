Feature: This test should find an Artist by given regex

  Scenario: Find proper Artist by regex
    Given there is one artist
    And another one with different name
    When begginning of his name is passed as "Jo.."
    Then we should find this artist
    But I should not see more than one artist
