Feature: CountriesUsingCurrency operation

  Scenario: CountriesUsingCurrency
    Given the SOAP service is running
    When I send a SOAP request to CountriesUsingCurrency
    Then I should receive a response from CountriesUsingCurrency
