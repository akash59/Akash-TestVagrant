Feature: Verify Google Search feature

  @Sanity
  Scenario:  Verify search suggestions on Google Home page for keyword "india"
    When the user search for the item in google "india"
    And user click suggestion "5" from the list
    Then results stats should be displayed