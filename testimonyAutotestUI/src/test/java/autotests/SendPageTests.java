package autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SendPageTests extends TestBase {

    @Test
    public void checkResultValueAfterClickSubmitButton() {
        app.getSendPage().inputDate("02022020");
        app.getSendPage().inputColdWater("5");
        app.getSendPage().inputHotWater("5");
        app.getSendPage().inputGas("5");
        app.getSendPage().inputElectric("5");
        app.getSendPage().clickSubmitButton();
        Assertions.assertEquals(app.getSendPage().getResultValue(), "-194491425");
    }

    @Test
    public void checkAllCellInResultTableAfterClickSubmitButton() {
        app.getSendPage().fillInAllFields("02022020", "5", "5", "5", "5");
        app.getSendPage().clickSubmitButton();
        app.getSendPage().checkAllCellInResultTable();
    }
}
