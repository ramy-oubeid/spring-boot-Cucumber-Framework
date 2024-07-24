Feature: CountryName operation

  Scenario: CountryName
    Given the SOAP service is running
    When I send a SOAP request to CountryName
    Then I should receive a response from CountryName
