Feature: Company
  Business rules for the Company entity and import process 

  Scenario: Success to save a new companies
    Given the following companies:
      | companyCod | name                             | phone        | ceo                 |
      |            | AIR PRODUCTS AND CHEMICALS, INC. | 447563444783 | William Albuquerque |
      |            | AUTOZONE, INC.                   | 447823464731 | Kaio Grealo         |
      |            | BAXTER INTERNATIONAL, INC.       | 422223224185 | Juan Ramiro         |
    When save the company
    Then there are 3 companies saved 

  Scenario: The system avoids duplicates, inserting when it is new and finding when it exists
    Given the following companies:
      | companyCod | name                             | phone        | ceo                 |
      |            | BAXTER INTERNATIONAL, INC.       | 422223224185 | Juan Ramiro         |
      |            | BAXTER INTERNATIONAL, INC.       | 422223224185 | Juan Ramiro         |
    When save the company avoiding duplicates
    Then there are 1 companies saved 
  