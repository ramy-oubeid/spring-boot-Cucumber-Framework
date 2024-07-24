Feature: ListOfContinentsByName operation

  Scenario: ListOfContinentsByName
    Given the SOAP service is running
    When I send a SOAP request to ListOfContinentsByName
    Then I should receive a response from ListOfContinentsByName
