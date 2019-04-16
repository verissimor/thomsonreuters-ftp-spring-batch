Feature: Docket
  Business rules for the Docket entity and import process 

  Scenario: The field price eod shoud be positive
    Given a docket with price eod "-1.0"
    When save the docket
    Then an error is showed about price eod 

  