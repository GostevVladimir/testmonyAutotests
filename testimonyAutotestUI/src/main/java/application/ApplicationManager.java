package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import pages.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver driver;
    private MainPage mainPage;
    private PricePage pricePage;
    private HistoryPage historyPage;
    private SendPage sendPage;
    private Elements elements;
    private Properties properties;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/main/resources/%s.properties", target))));

        if (browser.equals(BrowserType.CHROME)) {
            if (browser.equals(BrowserType.CHROME)) {
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                driver = new InternetExplorerDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        mainPage = new MainPage(driver);
        pricePage = new PricePage(driver);
        historyPage = new HistoryPage(driver);
        sendPage = new SendPage(driver);
        elements = new Elements(driver);
    }


    public MainPage getMainPage() {
        return mainPage;
    }

    public PricePage getPricePage() {
        return pricePage;
    }

    public HistoryPage getHistoryPage() {
        return historyPage;
    }

    public SendPage getSendPage() {
        return sendPage;
    }

    public Elements getElements() {
        return elements;
    }

    public void stop() {
        driver.quit();
    }
}
