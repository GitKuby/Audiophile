Feature: This feature should delete given artists from db
    
  Scenario Outline: Delete artists from db
    Given we add artis with <id>, <name> and <birthYear>
    When we put his <id> to a removal list
    Then he should not be visible in db
    But the other one should stay

     Examples:
          |  id |  name     | birthYear |
          |  1  |  "Luke"   |   1980    |
          |  2  |  "Freddy" |   1977    |
