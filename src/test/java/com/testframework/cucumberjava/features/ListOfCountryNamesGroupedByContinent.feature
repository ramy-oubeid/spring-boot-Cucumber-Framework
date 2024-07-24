Feature: ListOfCountryNamesGroupedByContinent operation

  Scenario: ListOfCountryNamesGroupedByContinent
    Given the SOAP service is running
    When I send a SOAP request to ListOfCountryNamesGroupedByContinent
    Then I should receive a response from ListOfCountryNamesGroupedByContinent
