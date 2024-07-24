Feature: ListOfContinentsByCode operation

  Scenario: ListOfContinentsByCode
    Given the SOAP service is running
    When I send a SOAP request to ListOfContinentsByCode
    Then I should receive a response from ListOfContinentsByCode
