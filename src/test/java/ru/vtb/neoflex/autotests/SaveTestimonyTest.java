package ru.vtb.neoflex.autotests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.CurrentTestimony;
import ru.neoflex.model.RequestSaveTestimony;
import ru.neoflex.model.ResponseSaveTestimony;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveTestimonyTest {
    String saveTestimonyURI = "http://localhost:8080/services/testimony/save";

    public RequestSaveTestimony createBodyForRequestSaveTestimony() {
        RequestSaveTestimony requestSaveTestimony = new RequestSaveTestimony();
        CurrentTestimony currentTestimony = new CurrentTestimony();

        requestSaveTestimony.setDate("02-2020");
        currentTestimony.setColdWater(30);
        currentTestimony.setHotWater(40);
        currentTestimony.setGas(50);
        currentTestimony.setElectricity(60);
        requestSaveTestimony.setCurrentTestimony(currentTestimony);
        return requestSaveTestimony;
    }

    @Test
    public void checkCodeSuccessTest() {
        int actualStatusCode = RequestTestController.getRequestCode(saveTestimonyURI, createBodyForRequestSaveTestimony());

        Assertions.assertEquals(200, actualStatusCode);
    }

    @Test
    public void checkFaultCodeSuccessTest() throws SQLException {
        ResponseSaveTestimony responseSaveTestimony = RequestTestController.getResponseBodySave(saveTestimonyURI, createBodyForRequestSaveTestimony());
        String resultCode = responseSaveTestimony.getFaultcode().getResultCode();
        String resultText = responseSaveTestimony.getFaultcode().getResultText();
        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);

        ResultSet expectedResult = MySqlConnector.selectAllFromBilling(createBodyForRequestSaveTestimony().getDate());
        while (expectedResult.next()) {
            String date = expectedResult.getString("currentmonth");
            double coldWater = expectedResult.getInt("coldWater");
            double hotWater = expectedResult.getInt("hotWater");
            double gas = expectedResult.getInt("gas");
            double electricity = expectedResult.getInt("electricity");
            Assertions.assertEquals(date, createBodyForRequestSaveTestimony().getDate());
            Assertions.assertEquals(coldWater, createBodyForRequestSaveTestimony().getCurrentTestimony().getColdWater());
            Assertions.assertEquals(hotWater, createBodyForRequestSaveTestimony().getCurrentTestimony().getHotWater());
            Assertions.assertEquals(gas, createBodyForRequestSaveTestimony().getCurrentTestimony().getGas());
            Assertions.assertEquals(electricity, createBodyForRequestSaveTestimony().getCurrentTestimony().getElectricity());
        }
    }
}
