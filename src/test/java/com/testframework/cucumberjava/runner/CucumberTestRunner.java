package com.testframework.cucumberjava.runner;

import com.testframework.cucumberjava.steps.WsdlParserRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.testframework.cucumberjava.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports",
                "html:target/extra-cucumber-reports",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags = "@soapTest"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    private static ExtentReports extent;

    private WsdlParserRunner wsdlParserRunner;

    // No-argument constructor
    public CucumberTestRunner() {
        super();
    }

    // Existing constructor
    public CucumberTestRunner(WsdlParserRunner wsdlParserRunner) {
        this.wsdlParserRunner = wsdlParserRunner;
    }

    @BeforeClass
    public void setup() throws Exception {
        // Initialize wsdlParserRunner here if necessary
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports/extentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Extent Report for Cucumber Tests");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("Cucumber Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterClass
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}