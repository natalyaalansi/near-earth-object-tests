# Test UI automation project for [CNEOS](https://cneos.jpl.nasa.gov/)
<img alt="cneos logo" src="https://cneos.jpl.nasa.gov/images/cneos_logo.png" />

## About CNEOS
The Center for Near-Earth Object Studies (CNEOS) computes high precision orbits for Near-Earth Objects (NEOs), predicts their future motions, assesses their impact hazard, and makes these result available on this website.

## Contents
- <a href="#technologies">Tools and technologies</a>
- <a href="#testcases">Executed automation test cases</a>
- <a href="#running">Running Autotests</a>
- <a href="#report">Allure Report</a>
- <a href="#testops">Allure TestOps integration</a>
- <a href="#jira">Jira integration</a>
- <a href="#telegram">Telegram notifications via bot</a>
- <a href="#video">Selenoid video of test run</a>

<a id="technologies"></a>
## Tools and technologies
 Java                                                                                                       | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | Selenide                                                                                                         | Selenoid                                                                                                                  | Allure <br> Report                                                                                                         |  Jenkins                                                                                                        |   Jira                                                                                                              | Telegram                                                                                                            |Allure <br> TestOps                                                                                                          
|:----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  | <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> |<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="images\logo\Telegram.svg" width="50" height="50" alt="Telegram"/></a> |<a href="https://qameta.io/"><img src="images\logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |
- To create autotests in this project the <code>[Java](https://www.java.com/)</code> language was used.
- <code>[Gradle](https://gradle.org/)</code> was used as an automatic build system.
- Frameworks <code>[JUnit5](https://junit.org/junit5/)</code> and <code>[Selenide](https://selenide.org/)</code> for automated testing of web applications have been applied.
- Browsers were launched via <code>[Selenoid](https://aerokube.com/selenoid/)</code>.
- To run tests remotely a job was implemented in <code>[Jenkins](https://jenkins.autotests.cloud/job/MaryPimenova-VacancyProjectUnit14/)</code> with the creation of an <code>[Allure-report](https://jenkins.autotests.cloud/job/MaryPimenova-VacancyProjectUnit14/7/allure/)</code> and sending the results to <code>[Telegram](https://web.telegram.org/)</code> using special Telegram bot.
- Integrations with с <code>[Allure TestOps](https://allure.autotests.cloud/project/2434/dashboards)</code> and <code>[Jira](https://jira.autotests.cloud/browse/HOMEWORK-720)</code> were implemented.

<a id="testcases"></a>
## Executed automation test cases
- Header navigation contains menu and breadcrumbs
- Page content consists of required blocks
- Updating the results table according to default filters is disabled
- The default filters are displayed, after resetting the filters
- The table contains the results when specific filters are applied
- The table does not contain the results when specific filters are applied
- Distance and Velocity header columns contain modified units respectively (Parameterized using the @CsvSource annotation)

<a id="running"></a>
## Running Autotests

### Local run
```
gradle clean test -Denv=local 
```

### [Remote Jenkins run](https://jenkins.autotests.cloud/job/near-earth-object-tests/)
```
gradle clean test -Denv=remote 
```

#### Properties are retrieved from the corresponding configuration file (depending on the value of `env`):

```
./resources/config/${env}.properties
```

<a id="report"></a>
## <img alt="Allure Reports" src="images/logo/Allure.svg" width="40" height="40"/> [Allure Report](https://jenkins.autotests.cloud/job/near-earth-object-tests/7/allure/)
<img title="Allure Overview" src="images/attachment/allureOverview.png"> 
<img title="Allure Suites" src="images/attachment/allureSuites.png"> 

<a id="testops"></a>
## <img alt="Allure TestOps" src="images/logo/Allure_TO.svg" width="40" height="40"/> [Allure TestOps integration](https://allure.autotests.cloud/project/4112/dashboards)
<img title="TestOps Results" src="images/attachment/testsOpsResults.png"> 
<img title="TestOps Suites" src="images/attachment/testOpsSuites.png"> 

<a id="jira"></a>
## <img alt="Jira" src="images/logo/Jira.svg" width="40" height="40"/> [Jira integration](https://jira.autotests.cloud/browse/HOMEWORK-1153)
<img title="Jira integration" src="images/attachment/jira.png"> 

<a id="telegram"></a>
## <img alt="Telegram" src="images/logo/Telegram.svg" width="40" height="40"/>Telegram notifications via bot 
<img title="Telegram notifications via bot" src="images/attachment/telegram.png">  

<a id="video"></a>
## <img alt="Selenoid" src="images/logo/Selenoid.svg" width="40" height="40"/>Selenoid video of test run
<img title="Selenoid video of test run" src="images/attachment/video.gif"> 