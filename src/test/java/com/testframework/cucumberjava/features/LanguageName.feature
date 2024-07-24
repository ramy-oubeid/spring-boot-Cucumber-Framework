Feature: LanguageName operation

  Scenario: LanguageName
    Given the SOAP service is running
    When I send a SOAP request to LanguageName
    Then I should receive a response from LanguageName
