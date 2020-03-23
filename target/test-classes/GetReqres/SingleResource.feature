Feature: Verify Single Resource API endpoint

  @API
  Scenario: Get a Single Resource
    When I request Single Resource with method get
    Then single resource status should be 200
    And Single Resource is displayed