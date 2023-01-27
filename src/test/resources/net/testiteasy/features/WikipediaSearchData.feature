@@regression
Feature: Wikipedia search data

  As a regular user of the Wikipedia application, I want to be able to search any data.

  @smoke
  @regression
  Scenario: Open application and perform search for Appium search word
    Given user open application
    When welcome screen ready
    And user type in search line "Appium"
    And user waits for search results appears

  @smoke
  @regression
  Scenario: User can enter the word Appium in the search field and follow the first link that contains the text - Automation for Apps
    Given user open application
    When welcome screen ready
    And user type in search line "Appium"
    And user waits for search results appears
    And user waits for search result "Automation for Apps" appears
    And user opens an item with subtitle "Automation for Apps"
    Then the article with subtitle "Automation for Apps" is opened