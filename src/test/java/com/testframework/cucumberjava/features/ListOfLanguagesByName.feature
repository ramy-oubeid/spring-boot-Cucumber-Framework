Feature: ListOfLanguagesByName operation

  Scenario: ListOfLanguagesByName
    Given the SOAP service is running
    When I send a SOAP request to ListOfLanguagesByName
    Then I should receive a response from ListOfLanguagesByName
