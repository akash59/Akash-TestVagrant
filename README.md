# TestVagrant

# Concepts Included
- Parallel test runs
- Shared state across cucumber step definitions
- Dependency injection
- Page Object pattern
- Common web page interaction methods
- Common api interaction methods
- Commonly used test utility classes

# Tools
- Maven
- Cucumber-JVM
- TestNG
- Rest Assured
- Selenium Webdriver

# Requirements
In order to utilise this project you need to have the following installed locally:

- Maven 3
- Chrome and Firefox (UI tests use Chrome by default, can be changed by passing env variable -DBrowser=FIREFOX)
- Java 8

# Quickstart
The project is based on BDD  for API, UI testing.

To run, navigate to base directory and run:

- ```mvn clean install```
- ```mvn clean test```

# Reporting
Reports are written into target directory after a successful run.

UI acceptance tests result in a HTML report for each feature in TestVagrant/target/cucumber-html-reports/. In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.
