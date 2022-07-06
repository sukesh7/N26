# Pet Store Test Automation Framework

## Table of Contents
* [Installation](#installation)
* [Trigger Tests](#trigger-tests)
* [Test Report](#test-report)
* [Framework Feature Support](#framework-feature-support)
* [Test Report Details](#test-report-details)
* [Tech Stack](#tech-stack)
* [Choice of Tech Stack](#choice-of-tech-stack)
* [Test Coverage](#test-coverage)

### Installation
1. Clone this repo.
2. Read this `README.md` file.
3. Play the game.
4. Have fun.

### Trigger Tests
``` In Terminal or Commandline
./gradlew clean test
```

### Test Report
``` In Terminal or Commandline
./gradlew allureServe
```

### Framework Feature Support
* Supports parallel execution(set parallel attribute to methods in testng.xml).
* Test cases grouping(Smoke, Regression).

### Test Report Details
* Allure Test Report 
* Detailed Request and Responses logged.
* Feature wise segregation.

### Tech Stack
* Rest Assured
* Java
* TestNG
* Gradle
* Allure Reporting
* Lombok
* Jackson Databind

### Choice of Tech Stack
* TestNG with RestAssured and Java is one of the widely used test framework stack which makes the automation testing easy and robust.
It can be used as a hybrid framework which gives additional control to group and execute the testcase according to the requirement. Some other 
advantages include parallel testing, annotations, logs, reports, test case grouping, parameterization etc. Allure reports are used to 
generate well-defined reports with minimal configuration. Allure reports enhances the reporting with different views and request and response 
logs which would be easy to debug and analyse the failures.

### Test Coverage
* Positive flows with some negative test cases has been automated. 

