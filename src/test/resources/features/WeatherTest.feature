Feature: Verify Weather Details

  @Regression
  Scenario:  Verify Weather Report for city "New Delhi" with temperature variance of 6 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "New Delhi" from Pin your city section
    Then pinned city "New Delhi" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | New Delhi |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 0

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
  Scenario:  Verify Weather Report for city "Chandigarh" with temperature variance of 6 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "Chandigarh" from Pin your city section
    Then pinned city "Chandigarh" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | Chandigarh |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 6

  @Regression
  Scenario:  Verify Weather Report for city "Bengaluru" with temperature variance of 9 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "Bengaluru" from Pin your city section
    Then pinned city "Bengaluru" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | Bengaluru |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 9

  @Regression
  Scenario:  Verify Weather Report for city "Jaipur" with temperature variance of 7 degrees
    When user visits ndtv website
    And opens the weather section
    And selects the city "Jaipur" from Pin your city section
    Then pinned city "Jaipur" shall be shown on the map with temperature information
    Then get the weather details from the api
      | q     | Jaipur |
      | units | metric    |
    Then temperature difference shall comply with the variance threshold 7
