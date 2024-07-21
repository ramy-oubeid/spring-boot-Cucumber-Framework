package com.testframework.cucumberjava.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class WsdlParserService {

    public void parseWsdlAndGenerateTests(String wsdlPath, String outputDir) {
        try {
            File wsdlFile = new File(wsdlPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(wsdlFile);

            NodeList operationNodes = doc.getElementsByTagName("wsdl:operation");
            for (int i = 0; i < operationNodes.getLength(); i++) {
                Element operationElement = (Element) operationNodes.item(i);
                String operationName = operationElement.getAttribute("name");

                generateStepDefinition(operationName, outputDir);
                generateFeatureFile(operationName, outputDir);
                generateTestNGRunner(operationName, outputDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateStepDefinition(String operationName, String outputDir) {
        String stepDefinitionTemplate =
                "package com.testframework.cucumberjava.generated.steps;\n\n" +
                        "import io.cucumber.java.en.When;\n" +
                        "import io.cucumber.java.en.Then;\n" +
                        "import org.springframework.beans.factory.annotation.Autowired;\n" +
                        "import org.springframework.ws.client.core.WebServiceTemplate;\n" +
                        "import static org.junit.jupiter.api.Assertions.*;\n\n" +
                        "public class %sSteps {\n\n" +
                        "    @Autowired\n" +
                        "    private WebServiceTemplate webServiceTemplate;\n\n" +
                        "    private String response;\n\n" +
                        "    @When(\"I send a SOAP request to %s\")\n" +
                        "    public void iSendASOAPRequestTo%s() {\n" +
                        "        // Code to send SOAP request\n" +
                        "        String request = \"...\"; // Replace with actual request\n" +
                        "        response = webServiceTemplate.marshalSendAndReceive(request).toString();\n" +
                        "    }\n\n" +
                        "    @Then(\"I should receive a response from %s\")\n" +
                        "    public void iShouldReceiveAResponseFrom%s() {\n" +
                        "        // Code to verify SOAP response\n" +
                        "        assertNotNull(response);\n" +
                        "    }\n" +
                        "}\n";

        String stepDefinitionContent = String.format(stepDefinitionTemplate, operationName, operationName, operationName, operationName, operationName);

        try (FileWriter writer = new FileWriter(outputDir + "/steps/" + operationName + "Steps.java")) {
            writer.write(stepDefinitionContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateFeatureFile(String operationName, String outputDir) {
        String featureTemplate =
                "Feature: %s operation\n\n" +
                        "  Scenario: %s\n" +
                        "    Given the SOAP service is running\n" +
                        "    When I send a SOAP request to %s\n" +
                        "    Then I should receive a response from %s\n";

        String featureContent = String.format(featureTemplate, operationName, operationName, operationName, operationName);

        try (FileWriter writer = new FileWriter(outputDir + "/features/" + operationName + ".feature")) {
            writer.write(featureContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateTestNGRunner(String operationName, String outputDir) {
        String runnerTemplate =
                "package com.testframework.cucumberjava.generated.runners;\n\n" +
                        "import io.cucumber.testng.AbstractTestNGCucumberTests;\n" +
                        "import io.cucumber.testng.CucumberOptions;\n" +
                        "import org.testng.annotations.AfterClass;\n" +
                        "import org.testng.annotations.BeforeClass;\n" +
                        "import io.qameta.allure.testng.AllureTestNg;\n" +
                        "import org.testng.annotations.Listeners;\n\n" +
                        "@Listeners({AllureTestNg.class})\n" +
                        "@CucumberOptions(\n" +
                        "        features = \"src/test/resources/features/%s.feature\",\n" +
                        "        glue = \"com.testframework.cucumberjava.generated.steps\",\n" +
                        "        plugin = {\n" +
                        "                \"pretty\",\n" +
                        "                \"html:target/cucumber-reports\",\n" +
                        "                \"json:target/cucumber.json\",\n" +
                        "                \"junit:target/cucumber-results.xml\",\n" +
                        "                \"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm\"\n" +
                        "        },\n" +
                        "        monochrome = true,\n" +
                        "        tags = \"@soapTest\"\n" +
                        ")\n" +
                        "public class %sTestNGRunner extends AbstractTestNGCucumberTests {\n\n" +
                        "    @BeforeClass\n" +
                        "    public void setup() {\n" +
                        "        // Any setup code if necessary\n" +
                        "    }\n\n" +
                        "    @AfterClass\n" +
                        "    public void tearDown() {\n" +
                        "        // Any teardown code if necessary\n" +
                        "    }\n" +
                        "}\n";

        String runnerContent = String.format(runnerTemplate, operationName, operationName);

        try (FileWriter writer = new FileWriter(outputDir + "/runners/" + operationName + "TestNGRunner.java")) {
            writer.write(runnerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

