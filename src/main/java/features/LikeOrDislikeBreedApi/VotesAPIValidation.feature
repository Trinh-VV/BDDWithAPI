#Author: TrinhVV
@VotesApi
Feature: Votes Api Validation

  @HappyCase
  Scenario Outline: Send vote request successfully
    Given Send Api request with data
      | URL                                | Method | ApiKey    | ApiValue     | RequestBodyName       |
      | https://api.thecatapi.com/v1/votes | POST   | x-api-key | DEMO-API-KEY | VotesRequestBody.json |
    When Send API
    Then Response returns <StatusCode> and <Message>
    And SubId is Exactly

    Examples: 
    
      | StatusCode | Message |
      |        201 | SUCCESS |
