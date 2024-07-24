Feature: CapitalCity operation

  Scenario: CapitalCity
    Given the SOAP service is running
    When I send a SOAP request to CapitalCity
    Then I should receive a response from CapitalCity
