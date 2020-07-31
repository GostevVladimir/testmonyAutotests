package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.Price;
import ru.neoflex.model.RequestChangePrice;
import ru.neoflex.model.ResponseChangePrice;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import static ru.vtb.neoflex.autotests.TestBase.validRequestChangeTestimony;

public class ChangePriceTest {
    String changePriceURI = "http://localhost:8080/services/testimony/changePrice";

    public RequestChangePrice createBodyForRequestChangePrice() {
        RequestChangePrice requestChangePrice = new RequestChangePrice();
        Price price = new Price();

        price.setPriceColdWater(1050);
        price.setPriceHotWater(123);
        price.setPriceGas(124);
        price.setPriceElectricity(12412412);
        requestChangePrice.setPrice(price);
        return requestChangePrice;
    }

    public static Iterator<Object[]> dataRead() throws IOException {
        String requestFile = "src/test/resources/ChangePriceTest.json";
        return validRequestChangeTestimony(requestFile);
    }

    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkCodeSuccessForChangePriceTest(RequestChangePrice requestChangePrice) {
        int actualStatusCode = RequestTestController.getRequestCodeForChangePrice(changePriceURI, requestChangePrice);

        Assertions.assertEquals(200, actualStatusCode);
    }


    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkFaultCodeSuccessForChangePriceTest(RequestChangePrice requestChangePrice) {
        ResponseChangePrice responseChangePrice = RequestTestController.getResponseBodyForChangePrice(changePriceURI, requestChangePrice);
        String resultCode = responseChangePrice.getResultCode();
        String resultText = responseChangePrice.getResultText();

        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
    }

    @MethodSource("dataRead")
    @ParameterizedTest
    public void checkingCorrectnessWritingToPriceGuideTable(RequestChangePrice requestChangePrice) throws SQLException {
        RequestTestController.getResponseBodyForChangePrice(changePriceURI, requestChangePrice);
        ResultSet expectedResult = MySqlConnector.selectAllFromPrice();
        while (expectedResult.next()) {
            double priceColdWater = expectedResult.getInt("priceColdWater");
            double priceHotWater = expectedResult.getInt("priceHotWater");
            double priceGas = expectedResult.getInt("priceGas");
            double priceElectricity = expectedResult.getInt("priceElectricity");
            Assertions.assertEquals(priceColdWater, requestChangePrice.getPrice().getPriceColdWater());
            Assertions.assertEquals(priceHotWater, requestChangePrice.getPrice().getPriceHotWater());
            Assertions.assertEquals(priceGas, requestChangePrice.getPrice().getPriceGas());
            Assertions.assertEquals(priceElectricity, requestChangePrice.getPrice().getPriceElectricity());
        }
    }
}
