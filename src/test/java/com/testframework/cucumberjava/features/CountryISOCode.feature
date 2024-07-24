Feature: CountryISOCode operation

  Scenario: CountryISOCode
    Given the SOAP service is running
    When I send a SOAP request to CountryISOCode
    Then I should receive a response from CountryISOCode
