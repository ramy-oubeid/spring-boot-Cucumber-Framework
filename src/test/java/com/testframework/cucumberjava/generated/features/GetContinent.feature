Feature: GetContinent operation

  Scenario: GetContinent
    Given the SOAP service is running
    When I send a SOAP request to GetContinent
    Then I should receive a response from GetContinent
