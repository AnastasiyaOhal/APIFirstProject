Feature: Verify Single User API endpoint

  @API
  Scenario: Get a Single User
    When I request Single User with method get
    Then single user status should be 200
    And Single User is displayed