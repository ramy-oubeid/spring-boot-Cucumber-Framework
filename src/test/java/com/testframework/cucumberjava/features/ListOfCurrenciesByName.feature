Feature: ListOfCurrenciesByName operation

  Scenario: ListOfCurrenciesByName
    Given the SOAP service is running
    When I send a SOAP request to ListOfCurrenciesByName
    Then I should receive a response from ListOfCurrenciesByName
