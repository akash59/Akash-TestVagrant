Feature: Verify Weather Details

  @Sanity
  Scenario:  Verify Weather Report for city "New Delhi"
    When user visits ndtv website
    And opens the weather section
    Then selects the city "New Delhi" from Pin your city section
    Then pinned city "New Delhi" shall be shown on the map with temperature information