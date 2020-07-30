package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.model.ResponseOldTestimony;

public class OldTestimonyTest {
    String oldTestimonyURI = "http://localhost:8080/services/testimony/get/old/testimony/02-2020";

    @Test
    public void checkCodeSuccessForOldTestimonyTest() {

        int actualStatusCode = RequestTestController.getRequestCodeForOldTestimony(oldTestimonyURI);
        Assertions.assertEquals(200, actualStatusCode);
    }

    @Test
    public void checkFaultCodeSuccessForOldTestimonyTest() {

        ResponseOldTestimony responseEarlyReadings = RequestTestController.getRequestBodyForOldTestimony(oldTestimonyURI);
        String resultCode = responseEarlyReadings.getFaultcode().getResultCode();
        String resultText = responseEarlyReadings.getFaultcode().getResultText();

        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
    }
}
