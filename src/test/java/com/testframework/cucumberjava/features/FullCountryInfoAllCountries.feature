Feature: FullCountryInfoAllCountries operation

  Scenario: FullCountryInfoAllCountries
    Given the SOAP service is running
    When I send a SOAP request to FullCountryInfoAllCountries
    Then I should receive a response from FullCountryInfoAllCountries
