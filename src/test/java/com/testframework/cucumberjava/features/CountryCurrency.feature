Feature: CountryCurrency operation

  Scenario: CountryCurrency
    Given the SOAP service is running
    When I send a SOAP request to CountryCurrency
    Then I should receive a response from CountryCurrency
