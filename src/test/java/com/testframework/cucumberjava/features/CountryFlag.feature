Feature: CountryFlag operation

  Scenario: CountryFlag
    Given the SOAP service is running
    When I send a SOAP request to CountryFlag
    Then I should receive a response from CountryFlag
