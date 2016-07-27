Feature: Convert a sheet

  Scenario: From Yaml to JSON

    Given a sheet file dai-blackthorn.yml
    When I convert it to json
    Then I expect a point total of 258