package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Elements extends BasePage {

    public Elements(WebDriver wd) {
        super(wd);
    }

    private By backButton = By.xpath("//*[@id='back_button']");
    private By headerPage = By.xpath("/html/body/h1");

    public void clickBackButton() {
        waitForElementClickable(driver.findElement(backButton), driver);
        click(driver.findElement(backButton));
    }

    public String getHeaderPage() {
        return getHeaderPage(driver.findElement(headerPage), driver);
    }
}
