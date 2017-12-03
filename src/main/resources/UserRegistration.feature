Feature: This feature should register an user
    
  Scenario Outline: Register and verify
    Given registration form
    When we add an user with <gender>, <firstName>,<lastName>, <pass>, <day>, <month>, <year>, <street>, <city>, <state>, <zip>, <country> and <mobile>
    Then we should see user's account

     Examples:
          | gender | firstName | lastName | pass   | day|month|year| street  | city     | state    | zip | country       | mobile  |
          |  "M"   |  "Luke"   | "Lukeson"|"secret"| 11 |8    |1920|"Street1"|"New York"|"New York"|11234|"United States"|111222333|
          |  "M"   |  "Freddy" | "Bush"   |"secret"| 23 |12   |1970|"Street1"|"New York"|"New York"|12345|"United States"|333567222|