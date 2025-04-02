<div>
  <p align="center">
    <img src="https://github.com/mmeest/Playtech/blob/main/Play.png" height="128px">
  </p>
</div>


# JAVA tests for Playtech

## Requirements:

1. Using Java and Selenium

2. Sharing the code in GitHub

3. Clean, commented code

## Bonus tasks can be completed as either part of the main task (where applicable) or separately.

Main task: 1. Open a web browser at the URL https://www.playtechpeople.com;

2. Find how many locations are under the „Locations“ tab and list them all.

3. In the „Who we are“ section under „Life at Playtech“, find the Casino product suite section on the page and print out the description text of the Casino unit.

4. Under „All Jobs“, print out a link for an available position in Estonia from both Tartu and Tallinn.

5. Close the browser.

## Bonus tasks (can be completed as part of the main task, where applicable, or separately):

1. Perform all clicking actions by clicking on specific coordinates (for verification, please mark the resolution of your screen in the test).

2. Export the result of the test as a .txt file.

3. Add the project into the JUnit framework, so that you will run unit tests instead of Java application. 

--------

## Solution

For running the tests:

```
mvn test
```

## Tools used:

* JAVA
* Maven
* Selenium

Tests are located in 'NewTest'
Page Object models are located in 'pages' folder:
* MainPage
* WhoWeArePage
* AllJobsPage

Bonus task coordinates will be written in .txt file named in format: 

"2025-04-01_22-45-13_Clicking_Coordinates.txt"

So that each file name contains date and time created.

