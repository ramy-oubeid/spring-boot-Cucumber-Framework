# Cucumber Java SOAP Testing Framework

## Overview

This project is a Spring Boot application designed to automate the generation of Cucumber BDD tests for SOAP web services. The application reads a WSDL file, parses it to identify the SOAP operations, and generates Cucumber feature files and step definitions accordingly. It also integrates with TestNG for parallel test execution and uses Allure for reporting.

## Key Components

- **WsdlParserService.java**: Service to parse the WSDL and generate Cucumber feature files and step definitions.
- **WsdlParserRunner.java**: CommandLineRunner to trigger the WSDL parsing and file generation.
- **AppConfig.java**: Spring configuration class to set up the WebServiceTemplate.
- **CucumberSpringConfiguration.java**: Cucumber configuration class for integrating Spring context.
- **CucumberjavaApplication.java**: The main Spring Boot application class.

## Prerequisites

- Java 17
- Maven

## Dependencies

The `pom.xml` includes the following dependencies:

- Spring Boot
- Spring WS
- Cucumber
- TestNG
- Allure
- Extent Reports
- SAAJ (SOAP with Attachments API for Java)

## Project Structure

```plaintext
src
|-- main
|   |-- java
|   |   `-- com
|   |       `-- testframework
|   |           `-- cucumberjava
|   |               |-- config
|   |               |   `-- AppConfig.java
|   |               |-- service
|   |               |   `-- WsdlParserService.java
|   |               `-- CucumberjavaApplication.java
|   |-- resources
|       |-- application.properties
|       `-- service.wsdl
|-- test
    |-- java
        `-- com
            `-- testframework
                `-- cucumberjava
                    |-- config
                    |   `-- CucumberSpringConfiguration.java
                    |-- runner
                    |   `-- WsdlParserRunner.java
                    |-- generated
                        |-- steps
                        |   `-- <GeneratedStepDefinitions>.java
                        `-- features
                            |-- <GeneratedFeatureFiles>.feature
    |-- resources
        |-- features
