package Tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.WhoWeArePage;
import pages.AllJobsPage;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.junit.Assert.*;

public class NewTest {

    private static WebDriver driver;
    private MainPage mainPage;
    private WhoWeArePage whoWeArePage;
    private AllJobsPage allJobsPage;
    private final String homepage = "https://www.playtechpeople.com/";

    @BeforeClass
    public static void setupClass() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Before
    public void beforeEachTest() {
        System.out.println("Navigating to " + homepage);
        driver.get(homepage);
        driver.manage().window().setSize(new Dimension(1280, 800)); // Set size of browser window
        // Page object models:
        mainPage = new MainPage(driver);
        whoWeArePage = new WhoWeArePage(driver);
        allJobsPage = new AllJobsPage(driver);

        // For handeling Cookie(Monster) pop-up window :)
        try {
            // Defining allowAllButton
            WebElement allowAllButton = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection"));

            // Click event for allowing the cookies
            if (allowAllButton.isDisplayed()) {
                allowAllButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            System.out.println("Cookie consent not found or already accepted.");
        }
    }

    // Test C001: Validating number of locations in 'locations' dropdown in Chrome.
    @Test
    public void validateMainMenuLocationItemsCountInChrome() {
        System.out.println("Test 001: Validating number of locations in 'Locations' dropdown in Chrome.");
        int expectedResult = 16;
        int actualResult = mainPage.countLocationMenuElements();
        assertEquals(expectedResult, actualResult);

        System.out.println("Test C001 PASSED: Expected = " + expectedResult + " Actual = " + actualResult);
    }

    // Test C002: Printing out description text from Life at Playtech - Who we are - Casino.
    @Test
    public void descriptionUnderWhoWeAreFirstCard(){
        System.out.println("Test C002: Validating description text under 'Who We Are' first card.");

        mainPage.clickOnMenuItemLifeAtPlaytech();
        mainPage.clickOnMenuItemLifeAtPlaytechWhoWeAre();

        whoWeArePage = new WhoWeArePage(driver);

        // Printing out card text
        String cardText = whoWeArePage.returnCardDescriptionTextOfFirstCard();
        System.out.println("CASINO CARD DESCRIPTION: " + cardText);

        // Assertion
        assertNotNull("Card description should not be null", cardText);
    }

    // Test C003: Getting links of jobs in Estonia
    @Test
    public void findJobsInTallinnAndTartu(){
        System.out.println("Test C003: Finding jobs in Tallinn and Tartu.");

        // Clicking All Jobs button
        mainPage.clickOnButtonAllJobs();

        // Selectin jobs in Estonia
        allJobsPage.selectCountryFromDropDown();
        allJobsPage.clickOnSearchButton();

        System.out.println("Jobs in Estonia:");

        // Printing out jobs in Estonia
        allJobsPage.printJobsInEstonia();
    }

    // Test C004: Let's do some clicking actions
    @Test
    public void clickinOnRandomCoordinates(){
        System.out.println("Test C004: Clicking some random coordinates.");

        // We get browser window size
        // --- COMMENTED OUT - COORDINATES GO OUT OF BOUNDARIES ---
        // Dimension size = driver.manage().window().getSize();
        // int screenWidth = size.getWidth();      // Storing screen width into variable
        // int screenHeight = size.getHeight();    // Storing screen height into variable

        int screenWidth = 1280 - 100;           // Hard coded screen width value
        int screenHeight = 595 - 100;           // Hard coded screen height value
        int xPos = 0;                           // Variable for storing x coordinate
        int yPos = 0;                           // Variable for storing y coordinate

        // Generating actions instance for click events
        Actions actions = new Actions(driver);

        // Getting local date and time for adding it to txt file name
        LocalDateTime myObj = LocalDateTime.now();
        // Setting pattern of date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        // Storing date and time into string
        String localDateAndTime = myObj.format(formatter);

        try{
            // Adding new file writer object and creating new text document
            FileWriter myWriter = new FileWriter(localDateAndTime + "_Clicking_Coordinates.txt");
            myWriter.write("Browser window with: " + screenWidth + "\nBrowser window height: " + screenHeight + "\n");

            // WebDriverWait with 10 seconds
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Waiting page to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));

            // Loop for 10 clicks
            for(int i = 1; i <= 10; i++){
                xPos = magicRandomNumber(screenWidth);          // We'll generate random x position from screenWidth value
                yPos = magicRandomNumber(screenHeight);         // We'll generate random y position from screenHeight value

                // Performing click events
                actions.moveToLocation(xPos, yPos).click().perform();

                // Writing click coordinates to the file
                myWriter.write(i + ". X-position: " + xPos + " Y-position: " + yPos + "\n");
            }
            myWriter.close();       // Closing file writer

        // For error handling
        }catch(IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    // We'll close the driver
    @AfterClass
    public static void closeBrowser() {
        // Closing possible open driver object after test
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Finished testing.");
    }

    //--------------PRIVATE METHODS GO BELOW--------------

    // Generating random coordinates (NB! Easter egg: next Eurojackpot winning numbers will be generated here:)
    private static int magicRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
