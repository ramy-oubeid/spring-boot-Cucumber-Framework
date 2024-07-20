@soapTest
Feature: SOAP Request and Response

  Scenario: Sending SOAP request and receiving response
    Given I have a SOAP request
    When I send the SOAP request to the server
    Then I should get a valid SOAP response
    And the response should contain the country names and ISO codes

