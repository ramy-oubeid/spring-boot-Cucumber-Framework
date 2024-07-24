Feature: ListOfCountryNamesByCode operation

  Scenario: ListOfCountryNamesByCode
    Given the SOAP service is running
    When I send a SOAP request to ListOfCountryNamesByCode
    Then I should receive a response from ListOfCountryNamesByCode
