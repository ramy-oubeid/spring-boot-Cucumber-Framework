package com.testframework.cucumberjava.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class SoapRequestSteps {

    private String soapRequest;
    private String soapResponse;

    @Given("I have a SOAP request")
    public void i_have_a_soap_request() {
        soapRequest = """
            <?xml version="1.0" encoding="utf-8"?>
            <soap12:Envelope xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
              <soap12:Body>
                <ListOfCountryNamesByName xmlns="http://www.oorsprong.org/websamples.countryinfo">
                </ListOfCountryNamesByName>
              </soap12:Body>
            </soap12:Envelope>
            """;
    }

    @When("I send the SOAP request to the server")
    public void i_send_the_soap_request_to_the_server() throws Exception {
        URL url = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

        connection.getOutputStream().write(soapRequest.getBytes(StandardCharsets.UTF_8));

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);

        byte[] responseBytes = connection.getInputStream().readAllBytes();
        soapResponse = new String(responseBytes, StandardCharsets.UTF_8);
    }

    @Then("I should get a valid SOAP response")
    public void i_should_get_a_valid_soap_response() throws Exception {
        assertTrue(soapResponse.contains("<soap:Envelope"));
        assertTrue(soapResponse.contains("<soap:Body"));
    }

    @Then("the response should contain the country names and ISO codes")
    public void the_response_should_contain_the_country_names_and_ISO_codes() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(soapResponse.getBytes(StandardCharsets.UTF_8)));

        NodeList countryNodes = doc.getElementsByTagName("m:tCountryCodeAndName");
        assertTrue(countryNodes.getLength() > 0);

        for (int i = 0; i < countryNodes.getLength(); i++) {
            NodeList childNodes = countryNodes.item(i).getChildNodes();
            String isoCode = null;
            String name = null;
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeName().equals("m:sISOCode")) {
                    isoCode = childNodes.item(j).getTextContent();
                }
                if (childNodes.item(j).getNodeName().equals("m:sName")) {
                    name = childNodes.item(j).getTextContent();
                }
            }
            assertTrue(isoCode != null && !isoCode.isEmpty());
            assertTrue(name != null && !name.isEmpty());
        }
    }
}

