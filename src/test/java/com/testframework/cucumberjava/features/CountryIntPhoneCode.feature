Feature: CountryIntPhoneCode operation

  Scenario: CountryIntPhoneCode
    Given the SOAP service is running
    When I send a SOAP request to CountryIntPhoneCode
    Then I should receive a response from CountryIntPhoneCode
