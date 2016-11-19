Feature: Convert a sheet

  Scenario: dai-blackthorn from yml to json and check total points

    Given a sheet file dai-blackthorn.yml
    When I convert it to json
    Then I expect a point total of 258

  Scenario: dai-blackthorn from yml to json and check handedness

    Given a sheet file dai-blackthorn.yml
    When I convert it to json
    Then I expect a handedness that is "Right"

  Scenario: initial sheet from yml to json and check total points

    Given an initial sheet
    When I convert it to json
    Then I expect a point total of 0

  Scenario: add an advantage to the initial sheet and convert from yml to json and check total points

    Given an initial sheet
    And I add an advantage named Combat Reflexes
    When I convert it to json
    Then I expect a point total of 15