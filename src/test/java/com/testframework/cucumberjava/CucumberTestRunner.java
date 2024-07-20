package com.testframework.cucumberjava;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.testframework.cucumberjava.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports", // Existing HTML report
                "html:target/extra-cucumber-reports", // Extra HTML report
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"
        },
        monochrome = true,
        tags = "@soapTest" // Run only the SOAP tests
)
public class CucumberTestRunner {
}

