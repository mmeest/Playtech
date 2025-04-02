package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AllJobsPage {
    private final WebDriver driver;

    public AllJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Countries selection
    private WebElement getCountriesSelection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.search-column:nth-child(2)")));
    }

    // Search button
    private WebElement getSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".blue-button")));
    }

    // Selecting country from dropdown
    public void selectCountryFromDropDown() {
        WebElement countriesSelection = getCountriesSelection();
        countriesSelection.click();

        // Choosing Estonia
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement estoniaOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Estonia']")));
        estoniaOption.click();
    }

    // Search button eleement click
    public void clickOnSearchButton() {
        WebElement searchButton = getSearchButton();
        searchButton.click();
    }

    // Method for printing out jobs in Estonia (Tallinn/Tartu)
    public void printJobsInEstonia() {
        // We'll create a list of available job items from DOM
        List<WebElement> jobLinks = driver.findElements(By.cssSelector("a.job-item"));

        // We'll go thru all the job links and print out the visible ones
        for(WebElement job : jobLinks){
            if(job.isDisplayed()){
                System.out.println(job.getDomAttribute("href"));
            }
        }
    }
}

