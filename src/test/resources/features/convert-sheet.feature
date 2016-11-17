Feature: Convert a sheet

  Scenario: From Yaml to JSON point total

    Given a sheet file dai-blackthorn.yml
    When I convert it to json
    Then I expect a point total of 258

  Scenario: From Yaml to JSON handedness

    Given a sheet file dai-blackthorn.yml
    When I convert it to json
    Then I expect a handedness that is "Right"