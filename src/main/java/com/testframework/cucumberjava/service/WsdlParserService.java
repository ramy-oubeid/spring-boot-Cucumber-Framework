package com.testframework.cucumberjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(WsdlParserService.class);

    public void parseWsdlAndGenerateTests(String wsdlPath, String outputDir) {
        logger.info("***********WsdlParserService***********");
        logger.info("Starting WSDL parsing and test generation for WSDL at: {}", wsdlPath);
        try {
            File wsdlFile = new File(wsdlPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(wsdlFile);

            doc.getDocumentElement().normalize();

            NodeList portTypeNodes = doc.getElementsByTagNameNS("*", "portType");
            if (portTypeNodes.getLength() == 0) {
                logger.error("The WSDL document does not contain any portType elements!");
                return;
            }

            logger.info("The WSDL document is being read: {} portType elements found", portTypeNodes.getLength());

            for (int i = 0; i < portTypeNodes.getLength(); i++) {
                Element portTypeElement = (Element) portTypeNodes.item(i);
                NodeList operationNodes = portTypeElement.getElementsByTagNameNS("*", "operation");
                for (int j = 0; j < operationNodes.getLength(); j++) {
                    Element operationElement = (Element) operationNodes.item(j);
                    String operationName = operationElement.getAttribute("name");
                    logger.info("Processing operation: {}", operationName);

                    generateStepDefinition(operationName, outputDir);
                    generateFeatureFile(operationName, outputDir);
                    generateTestNGRunner(operationName, outputDir);
                }
            }
            logger.info("WSDL parsing and test generation completed.");
        } catch (Exception e) {
            logger.error("An error occurred while parsing the WSDL and generating tests", e);
        }
    }

    private void generateStepDefinition(String operationName, String outputDir) {
        logger.info("Generating step definition for operation: {}", operationName);
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

        try {
            File stepsDir = new File(outputDir + "/steps/");
            if (!stepsDir.exists()) {
                stepsDir.mkdirs();
            }
            try (FileWriter writer = new FileWriter(stepsDir + "/" + operationName + "Steps.java")) {
                writer.write(stepDefinitionContent);
                logger.info("Step definition file generated for operation: {}", operationName);
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing the step definition file for operation: {}", operationName, e);
        }
    }

    private void generateFeatureFile(String operationName, String outputDir) {
        logger.info("Generating feature file for operation: {}", operationName);
        String featureTemplate =
                "Feature: %s operation\n\n" +
                        "  Scenario: %s\n" +
                        "    Given the SOAP service is running\n" +
                        "    When I send a SOAP request to %s\n" +
                        "    Then I should receive a response from %s\n";

        String featureContent = String.format(featureTemplate, operationName, operationName, operationName, operationName);

        try {
            File featuresDir = new File(outputDir + "/features/");
            if (!featuresDir.exists()) {
                featuresDir.mkdirs();
            }
            try (FileWriter writer = new FileWriter(featuresDir + "/" + operationName + ".feature")) {
                writer.write(featureContent);
                logger.info("Feature file generated for operation: {}", operationName);
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing the feature file for operation: {}", operationName, e);
        }
    }

    private void generateTestNGRunner(String operationName, String outputDir) {
        logger.info("Generating TestNG runner for operation: {}", operationName);
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

        try {
            File runnersDir = new File(outputDir + "/runners/");
            if (!runnersDir.exists()) {
                runnersDir.mkdirs();
            }
            try (FileWriter writer = new FileWriter(runnersDir + "/" + operationName + "TestNGRunner.java")) {
                writer.write(runnerContent);
                logger.info("TestNG runner file generated for operation: {}", operationName);
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing the TestNG runner file for operation: {}", operationName, e);
        }
    }
}
