package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    protected WebDriver driver;

    // All five menu items as follows:
    private final WebElement menuItemLocations;
    private final WebElement menuItemLifeAtPlaytech;
    private final WebElement menuItemLifeAtPlaytechWhoWeAre;
    private final WebElement buttonAllJobs;

    private final List<WebElement> locationsMenuElements;

    public MainPage(WebDriver driver){
        this.driver = driver;

        // All menu items must be found after the driver is initialized
        this.menuItemLocations = driver.findElement(By.id("menu-item-82"));
        this.menuItemLifeAtPlaytech = driver.findElement(By.id("menu-item-49"));
        this.menuItemLifeAtPlaytechWhoWeAre = driver.findElement(By.id("menu-item-47"));
        this.buttonAllJobs = driver.findElement(By.className("yellow-button"));

        // Storing all location elements into list
        this.locationsMenuElements = driver.findElements(By.className("location-wrap__item--bottom"));
    }

    //------ Public methods below ------
    public int countLocationMenuElements(){
        return locationsMenuElements.size();
    }

    // Menu Item: Locations
    public void clickOnMenuItemLocations() {
        menuItemLocations.click();
    }

    // Menu Item: Life At Playtech
    public void clickOnMenuItemLifeAtPlaytech(){
        menuItemLifeAtPlaytech.click();
    }

    // Menu Item: Life At Playtech: Who We Are
    public void clickOnMenuItemLifeAtPlaytechWhoWeAre(){
        menuItemLifeAtPlaytechWhoWeAre.click();
    }

    // Menu button: All Jobs
    public void clickOnButtonAllJobs(){
        buttonAllJobs.click();
    }
}
