package pages;

import helpers.TableHelper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendPage extends Elements {
    public SendPage(WebDriver wd) {
        super(wd);
    }

    private By coldWaterInput = By.xpath("//*[@id='coldData']");
    private By hotWaterInput = By.xpath("//*[@id='hotData']");
    private By gasInput = By.xpath("//*[@id='gasData']");
    private By electricInput = By.xpath("//*[@id='elecData']");
    private By calendarInput = By.xpath("//*[@id='date']");
    private By submitButton = By.xpath("//*[@id='button']");

    private By table = By.xpath("//*[@id='table']");
    private By resultValue = By.xpath("//*[@id='table']//td[text()='Итого']/following-sibling::td");

    public void inputColdWater(String inputValue) {
        waitForElementClickable(driver.findElement(coldWaterInput), driver);
        inputText(driver.findElement(coldWaterInput), inputValue);
    }

    public void inputHotWater(String inputValue) {
        waitForElementClickable(driver.findElement(hotWaterInput), driver);
        inputText(driver.findElement(hotWaterInput), inputValue);
    }

    public void inputGas(String inputValue) {
        waitForElementClickable(driver.findElement(gasInput), driver);
        inputText(driver.findElement(gasInput), inputValue);
    }

    public void inputElectric(String inputValue) {
        waitForElementClickable(driver.findElement(electricInput), driver);
        inputText(driver.findElement(electricInput), inputValue);
    }

    public void clickSubmitButton() {
        waitForElementClickable(driver.findElement(submitButton), driver);
        click(driver.findElement(submitButton));
    }

    public void inputDate(String inputValue) {
        waitForElementClickable(driver.findElement(calendarInput), driver);
        driver.findElement(calendarInput).click();
        driver.findElement(calendarInput).sendKeys(inputValue);
    }

    public String getResultValue() {
        waitForElementPresent(driver.findElement(resultValue), driver);
        return driver.findElement(resultValue).getText();
    }

    public void fillInAllFields(String dateValue, String coldWaterValue, String hotWaterValue, String gasValue, String electricValue) {
        inputDate(dateValue);
        inputColdWater(coldWaterValue);
        inputHotWater(hotWaterValue);
        inputGas(gasValue);
        inputElectric(electricValue);
    }

    public void checkAllCellInResultTable() {
        TableHelper tableHelper = new TableHelper(driver.findElement(table), driver);

        Assertions.assertFalse(tableHelper.getValueFromCell(1, 1).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(1, 2).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(2, 1).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(2, 2).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(3, 1).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(3, 2).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(4, 1).isEmpty());
        Assertions.assertFalse(tableHelper.getValueFromCell(4, 2).isEmpty());
        Assertions.assertFalse(getResultValue().isEmpty());
    }
}
