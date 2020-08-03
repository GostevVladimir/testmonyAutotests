package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected final static int WAITING_TIME_IN_SECONDS = 30;

    public BasePage(WebDriver wd) {
        this.driver = wd;
    }

    protected void click(WebElement locator) {
        locator.click();
    }

    public static WebElement elementVisibility(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME_IN_SECONDS);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementPresent(WebElement element, WebDriver driver) {
        return (new WebDriverWait(driver, WAITING_TIME_IN_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementClickable(WebElement element, WebDriver driver) {
        return (new WebDriverWait(driver, WAITING_TIME_IN_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getHeaderPage(WebElement element, WebDriver driver) {
        waitForElementPresent(element, driver);
        return element.getText();
    }

    protected void inputText(WebElement locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = locator.getAttribute("value");
            if (!text.equals(existingText)) {
                locator.clear();
                locator.sendKeys(text);
            }
        }
    }
}
