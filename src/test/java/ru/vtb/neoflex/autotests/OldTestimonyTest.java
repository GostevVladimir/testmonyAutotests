package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.ResponseOldTestimony;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import static ru.vtb.neoflex.autotests.TestBase.validResponseChangeOldTestimony;

public class OldTestimonyTest {
    String oldTestimonyURI = "http://localhost:8080/services/testimony/get/old/testimony/02-2020";

    public static Iterator<Object[]> dataRead() throws IOException {
        String requestFile = "src/test/resources/OldTestimonyTest.json";
        return validResponseChangeOldTestimony(requestFile);
    }

    @Test
    public void checkCodeSuccessForOldTestimonyTest() {

        int actualStatusCode = RequestTestController.getRequestCodeForOldTestimony(oldTestimonyURI);
        Assertions.assertEquals(200, actualStatusCode);
    }

    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkFaultCodeSuccessForOldTestimonyTest(ResponseOldTestimony responseOldTestimony) {

        RequestTestController.getRequestBodyForOldTestimony(oldTestimonyURI);
        String resultCode = responseOldTestimony.getFaultcode().getResultCode();
        String resultText = responseOldTestimony.getFaultcode().getResultText();

        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
    }

    @Test
    public void checkingCorrectnessWritingToTestimonyHistoryTable() throws SQLException {
        ResponseOldTestimony responseOldTestimony = RequestTestController.getRequestBodyForOldTestimony(oldTestimonyURI);

        ResultSet expectedResult = MySqlConnector.selectAllFromTestimony();
        while (expectedResult.next()) {
            String currentMonth = expectedResult.getString("currentmonth");
            String previousMonth = expectedResult.getString("previous_month");
            double coldWater = expectedResult.getInt("coldWater");
            double hotWater = expectedResult.getInt("hotWater");
            double gas = expectedResult.getInt("gas");
            double electricity = expectedResult.getInt("electricity");
            double costColdWater = expectedResult.getInt("cost_coldWater");
            double costHotWater = expectedResult.getInt("cost_hotWater");
            double costGas = expectedResult.getInt("cost_gas");
            double costElectricity = expectedResult.getInt("cost_electricity");
            double totalCost = expectedResult.getInt("total_cost");

            Assertions.assertEquals(currentMonth, responseOldTestimony.getDate());
            Assertions.assertEquals(previousMonth, responseOldTestimony.getPreviousDate());
            Assertions.assertEquals(coldWater, responseOldTestimony.getConsumed().getColdWater());
            Assertions.assertEquals(hotWater, responseOldTestimony.getConsumed().getHotWater());
            Assertions.assertEquals(gas, responseOldTestimony.getConsumed().getGas());
            Assertions.assertEquals(electricity, responseOldTestimony.getConsumed().getElectricity());

            Assertions.assertEquals(costColdWater, Double.parseDouble(responseOldTestimony.getCost().getColdWater()));
            Assertions.assertEquals(costHotWater, Double.parseDouble(responseOldTestimony.getCost().getHotWater()));
            Assertions.assertEquals(costGas, Double.parseDouble(responseOldTestimony.getCost().getGas()));
            Assertions.assertEquals(costElectricity, Double.parseDouble(responseOldTestimony.getCost().getElectricity()));
            Assertions.assertEquals(totalCost, responseOldTestimony.getTotalCost());
        }
    }
}
