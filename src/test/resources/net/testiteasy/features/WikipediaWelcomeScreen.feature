@smoke
Feature: Wikipedia welcome screen

  As a regular user of the Wikipedia application, I want to be able to see the Welcome screen.

  @smoke
  @regression
  Scenario: Open application on the welcome screen
    Given user open application
    When welcome screen ready
    Then user can see Explore icon

  @smoke
  @regression
  Scenario: Open application on the welcome screen and make sure that all expected elements are present on the page
    Given user open application
    When welcome screen ready
    Then user can see the following icons
      | My lists |
      | Explore  |
      | History  |
      | Nearby   |
