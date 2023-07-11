# OrangeHRM_WebAutomation_with_Selenium_TestNG

In this project, a Demo site is automated using Selenium webdriver and TestNG testing framework. The project implements Page Object Model (POM), which is the best practice for automation. Allure reports have been generated and added here.

## Scenario
 - Login to orangeHRM demo site https://opensource-demo.orangehrmlive.com/ as admin
 - Create 1 new employee and save it to a JSON file
 - Now go to PIM page and search newly created employee by his/her username and ID. Assert that the user is found.
 - Logout the admin.
 - Login the newly created employee with proper credentials, extracted from jSON file
 - Employee go to MyInfo page and update his/her gender and blood group. Blood group will be O+.
 - Assert if update is done successfully.
 - Logout the session.

## Technology and Tools
 - Selenium webdriver
 - TestNG framework
 - Java language
 - Gradle build tool
 - Intellij IDEA IDE
 - Allure for report generation

## Way to run the project
  - Clone this project
  - Run the following command into the terminal: gradle clean test
  - For generating Allure report, use these commands: allure generate allure-results --clean -output and then allure serve allure-results

## PreRequisite
- TestNG, Selenium Webdriver,Java-8 or higher dependencies need to be installed.

## Allure Report
Part-1
![part_1 1](https://github.com/siratulmustakim/OrangeHRM_WebAutomation_with_Selenium_TestNG/assets/46200508/66c70583-2d82-4e90-8072-79999e266238)
![part_1 2](https://github.com/siratulmustakim/OrangeHRM_WebAutomation_with_Selenium_TestNG/assets/46200508/26632939-c3eb-41c0-aa75-2381e27af474)

Part-2
![part_2 1](https://github.com/siratulmustakim/OrangeHRM_WebAutomation_with_Selenium_TestNG/assets/46200508/c532589a-8a2a-4fbb-bb15-2615dc45a531)
![part_2 2](https://github.com/siratulmustakim/OrangeHRM_WebAutomation_with_Selenium_TestNG/assets/46200508/4e3f8140-2528-44d5-9ab0-8d2c01e5abc0)

