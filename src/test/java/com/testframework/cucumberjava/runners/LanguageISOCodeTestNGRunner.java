package com.testframework.cucumberjava.generated.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
@CucumberOptions(
        features = "src/test/resources/features/LanguageISOCode.feature",
        glue = "com.testframework.cucumberjava.generated.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        tags = "@soapTest"
)
public class LanguageISOCodeTestNGRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setup() {
        // Any setup code if necessary
    }

    @AfterClass
    public void tearDown() {
        // Any teardown code if necessary
    }
}
