package com.testframework.cucumberjava.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class LoginSteps {

    private WebDriver driver;

    @Given("I am on the Amazon login page")
    public void i_am_on_the_amazon_login_page() {
        // Setup WebDriverManager to manage Edge driver
        WebDriverManager.edgedriver().setup();


        // Initialize the Edge driver
        driver = new EdgeDriver();

        driver.get("https://www.amazon.com");
    }

    @When("I enter a valid username and password")
    public void i_enter_a_valid_username_and_password() {
        WebElement emailField = driver.findElement(By.id("ap_email"));
        emailField.sendKeys("your_email@example.com"); // Replace with your email
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement passwordField = driver.findElement(By.id("ap_password"));
        passwordField.sendKeys("your_password"); // Replace with your password
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        WebElement signInButton = driver.findElement(By.id("signInSubmit"));
        signInButton.click();
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        String expectedUrl = "https://www.amazon.com/?ref_=nav_ya_signin&";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
        driver.quit();
    }
}
