package autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PageNavigationTest extends TestBase {

    @Test
    public void fromMainToSendAndBack() {

        app.getMainPage().clickSend();
        Assertions.assertEquals(app.getSendPage().getHeaderPage(), "Передача показаний");
        app.getSendPage().clickBackButton();
        Assertions.assertEquals(app.getMainPage().getHeaderPage(), "Neo ЖКХ");
    }

    @Test
    public void fromMainToHistoryAndBack() {

        app.getMainPage().clickHistory();
        Assertions.assertEquals(app.getHistoryPage().getHeaderPage(), "История показаний");
        app.getHistoryPage().clickBackButton();
        Assertions.assertEquals(app.getMainPage().getHeaderPage(), "Neo ЖКХ");
    }

    @Test
    public void fromMainToPriceAndBack() {

        app.getMainPage().clickPrice();
        Assertions.assertEquals(app.getPricePage().getHeaderPage(), "Справочник стоимости услуг");
        app.getPricePage().clickBackButton();
        Assertions.assertEquals(app.getMainPage().getHeaderPage(), "Neo ЖКХ");
    }
}
