Feature: Verify List Resource API endpoint

  @API
  Scenario: Get a List of Resources
    When I request List Resource with method get
    Then list resource status should be 200
    And List of Resources is displayed