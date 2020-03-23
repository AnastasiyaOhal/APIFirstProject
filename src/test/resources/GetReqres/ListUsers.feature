Feature: Verify List Users API endpoint

  @API
  Scenario: Get a List of Users
    When I request List User with method get
    Then list user status should be 200
    And List of Users is displayed