package com.testframework.cucumberjava.steps;

import com.testframework.cucumberjava.CucumberjavaApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberjavaApplication.class)
public class CucumberSpringConfiguration {
    // This class remains empty and is used only as a configuration holder for Cucumber & Spring
}