Feature: Verify Single User Not Found API endpoint

  @API
  Scenario: Get a Single User Not Found
    When I request Single User Not Found with method get
    Then list user not found status should be 404
    And list user body is empty