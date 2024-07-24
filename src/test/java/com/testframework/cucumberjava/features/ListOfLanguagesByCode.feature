Feature: ListOfLanguagesByCode operation

  Scenario: ListOfLanguagesByCode
    Given the SOAP service is running
    When I send a SOAP request to ListOfLanguagesByCode
    Then I should receive a response from ListOfLanguagesByCode
