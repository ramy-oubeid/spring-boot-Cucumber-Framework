Feature: LanguageISOCode operation

  Scenario: LanguageISOCode
    Given the SOAP service is running
    When I send a SOAP request to LanguageISOCode
    Then I should receive a response from LanguageISOCode
