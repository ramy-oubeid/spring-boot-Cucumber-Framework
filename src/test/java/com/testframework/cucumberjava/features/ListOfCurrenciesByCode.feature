Feature: ListOfCurrenciesByCode operation

  Scenario: ListOfCurrenciesByCode
    Given the SOAP service is running
    When I send a SOAP request to ListOfCurrenciesByCode
    Then I should receive a response from ListOfCurrenciesByCode
