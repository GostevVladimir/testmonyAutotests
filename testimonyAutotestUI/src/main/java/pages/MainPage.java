package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Elements {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By dataSend = By.xpath("//*[@id='send_button']");
    private By dataHistory = By.xpath("//*[@id='history_button']");
    private By catalog = By.xpath("//*[@id='catalog_button']");

    public void clickSend() {
        click(driver.findElement(dataSend));
    }

    public void clickHistory() {
        click(driver.findElement(dataHistory));
    }

    public void clickPrice() {
        click(driver.findElement(catalog));
    }
}
