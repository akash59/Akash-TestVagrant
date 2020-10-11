Feature: Verify Weather Details

  @Regression
  Scenario:  Verify Weather Report for city "New Delhi" with temperature variance of 2 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "New Delhi" from Pin your city section
    Then pinned city "New Delhi" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | New Delhi |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 2

  @Regression
  Scenario:  Verify Weather Report for city "New Delhi" with temperature variance of 1 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "New Delhi" from Pin your city section
    Then pinned city "New Delhi" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | New Delhi |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 4

  @Regression
  Scenario:  Verify Weather Report for city "Chandigarh" with temperature variance of 3 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "Chandigarh" from Pin your city section
    Then pinned city "Chandigarh" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | Chandigarh |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 3

  @Regression
  Scenario:  Verify Weather Report for city "Bengaluru" with temperature variance of 3 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "Bengaluru" from Pin your city section
    Then pinned city "Bengaluru" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | Bengaluru |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 4
