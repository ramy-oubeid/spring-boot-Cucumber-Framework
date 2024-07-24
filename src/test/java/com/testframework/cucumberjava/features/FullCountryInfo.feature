Feature: FullCountryInfo operation

  Scenario: FullCountryInfo
    Given the SOAP service is running
    When I send a SOAP request to FullCountryInfo
    Then I should receive a response from FullCountryInfo
