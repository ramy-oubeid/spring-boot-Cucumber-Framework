Feature: CurrencyName operation

  Scenario: CurrencyName
    Given the SOAP service is running
    When I send a SOAP request to CurrencyName
    Then I should receive a response from CurrencyName
