Feature: This test should find an Artist by given regex

  Scenario: Find proper Artist by regex
    Given there is one artist
    When begginning of his name is passed as "Jo*"
    Then we should find this artist
