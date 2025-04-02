package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoWeArePage {
    protected WebDriver driver;
    private final WebElement body;

    public WhoWeArePage(WebDriver driver) {
        this.driver = driver;
        this.body = driver.findElement(By.tagName("body"));
    }

    // We'll get the first card
    private WebElement getFirstCard() {
        return driver.findElement(By.cssSelector(".product-cards > div:nth-child(1)"));
    }

    // We'll ensure visibility and scroll to card
    private void ensureCardVisibility(WebElement card) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(card));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", card);
    }

    // We'll scroll cards into view
    public void loadProductCards() {
        WebElement firstCard = getFirstCard();
        ensureCardVisibility(firstCard);
    }

    // Method for returning description of first card
    public String returnCardDescriptionTextOfFirstCard() {
        WebElement firstCard = getFirstCard();
        ensureCardVisibility(firstCard);

        // Getting description and returning the text
        WebElement description = firstCard.findElement(By.tagName("p"));
        return description.getText();
    }
}
