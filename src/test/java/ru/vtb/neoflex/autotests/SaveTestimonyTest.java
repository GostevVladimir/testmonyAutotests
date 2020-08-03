package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.CurrentTestimony;
import ru.neoflex.model.RequestSaveTestimony;
import ru.neoflex.model.ResponseSaveTestimony;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import static ru.vtb.neoflex.autotests.TestBase.validRequest;

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

    public static Iterator<Object[]> dataRead() throws IOException {
        String requestFile = "src/test/resources/SaveTestimonyTest.json";
        return validRequest(requestFile);
    }

    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkCodeSuccessTest(RequestSaveTestimony requestSaveTestimony) {
        int actualStatusCode = RequestTestController.getRequestCode(saveTestimonyURI, requestSaveTestimony);

        Assertions.assertEquals(200, actualStatusCode);
    }

    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkFaultCodeSuccessTest(RequestSaveTestimony requestSaveTestimony) throws SQLException {
        ResponseSaveTestimony responseSaveTestimony = RequestTestController.getResponseBodySave(saveTestimonyURI, requestSaveTestimony);
        String resultCode = responseSaveTestimony.getFaultcode().getResultCode();
        String resultText = responseSaveTestimony.getFaultcode().getResultText();
        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
        String date = null;
        double coldWater = 0;
        double hotWater = 0;
        double gas = 0;
        double electricity = 0;
        ResultSet expectedResult = MySqlConnector.selectAllFromBilling(requestSaveTestimony.getDate());
        while (expectedResult.next()) {
             date = expectedResult.getString("currentmonth");
             coldWater = expectedResult.getInt("coldWater");
             hotWater = expectedResult.getInt("hotWater");
             gas = expectedResult.getInt("gas");
             electricity = expectedResult.getInt("electricity");
        }
        Assertions.assertEquals(date, requestSaveTestimony.getDate());
        Assertions.assertEquals(coldWater, requestSaveTestimony.getCurrentTestimony().getColdWater());
        Assertions.assertEquals(hotWater, requestSaveTestimony.getCurrentTestimony().getHotWater());
        Assertions.assertEquals(gas, requestSaveTestimony.getCurrentTestimony().getGas());
        Assertions.assertEquals(electricity, requestSaveTestimony.getCurrentTestimony().getElectricity());
    }
}
