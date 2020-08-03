package autotests;

import application.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeEach
    public void setUp() throws Exception {
        app.init();
    }

    @AfterEach
    public void tearDown() {
        app.stop();
    }
}
