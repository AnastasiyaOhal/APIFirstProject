Feature: Verify Single Resource Not Found API endpoint

  @API
  Scenario: Get a Single Resource Not Found
    When I request Single Resource Not Found with method get
    Then resource status should be 404
    And resource body is empty