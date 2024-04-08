# Cucumber Java Selenium Rest-Assured Project

This project is a demonstration of automated testing using Cucumber, Java, and Selenium WebDriver. It allows for writing and executing automated tests in a behavior-driven development (BDD) style, making it easier for stakeholders to understand and collaborate on the testing process.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Writing Tests](#writing-tests)
- [Reporting](#reporting)

## Prerequisites

Before running the tests, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven
- Chrome or Firefox browser (depending on your WebDriver configuration)

## Setup

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, etc.).
3. Resolve dependencies by running `mvn clean install` from the project root directory.

## Running Tests

You can run the tests using Maven or directly from your IDE.


### IDE

1. Open the test runner class (`RunCucumberTest.java`) located in the `src/test/java` directory.
2. Right-click on the class and select "Run" or "Debug" depending on your preference.

## Project Structure

The project structure is organized as follows:

- `src/test/java`: Contains test code.
  - `features`: Contains feature files written in Gherkin syntax.
  - `stepDefinitions`: Contains step definition files corresponding to feature files.
  - `RunCucumberTest.java`: Test runner class that executes Cucumber scenarios.
- `src/test/resources`: Contains resources used in testing, such as configuration files.
- `pom.xml`: Maven project configuration file.

## Writing Tests

Tests in this project are written in Gherkin syntax. Each scenario is composed of steps, which are mapped to step definitions written in Java.

To write a new test:

1. Create a new feature file under `src/test/java/features`.
2. Write scenarios in Gherkin syntax.
3. Implement step definitions in corresponding Java classes under `src/test/java/stepDefinitions`.

## Reporting

Test execution results are generated in both text and HTML format. After running the tests, you can find the reports in the `target` directory:

- `target/cucumber/report.html`: HTML report containing test results.
- `target/cucumber.json`: json report.
