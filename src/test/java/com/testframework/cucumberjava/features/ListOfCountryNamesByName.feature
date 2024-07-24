Feature: ListOfCountryNamesByName operation

  Scenario: ListOfCountryNamesByName
    Given the SOAP service is running
    When I send a SOAP request to ListOfCountryNamesByName
    Then I should receive a response from ListOfCountryNamesByName
