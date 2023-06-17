#Author: TrinhVV

@GetCanadianUser
Feature: Getting Cadanian User Validation

  @HappyCase
  Scenario: Send a request with valid endpoint URL and method
    Given User has valid URL and Method
    When User sends a request
    Then Response is returned correctly
