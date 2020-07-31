package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.Price;
import ru.neoflex.model.RequestChangePrice;
import ru.neoflex.model.ResponseChangePrice;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Test
    public void checkCodeSuccessForChangePriceTest() {
        int actualStatusCode = RequestTestController.getRequestCodeForChangePrice(changePriceURI, createBodyForRequestChangePrice());

        Assertions.assertEquals(200, actualStatusCode);
    }


    @Test
    public void checkFaultCodeSuccessForChangePriceTest() {
        ResponseChangePrice responseChangePrice = RequestTestController.getResponseBodyForChangePrice(changePriceURI, createBodyForRequestChangePrice());
        String resultCode = responseChangePrice.getResultCode();
        String resultText = responseChangePrice.getResultText();

        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
    }

    @Test
    public void checkingCorrectnessWritingToPriceGuideTable() throws SQLException {
        ResultSet expectedResult = MySqlConnector.selectAllFromPrice();
        while (expectedResult.next()) {
            double priceColdWater = expectedResult.getInt("priceColdWater");
            double priceHotWater = expectedResult.getInt("priceHotWater");
            double priceGas = expectedResult.getInt("priceGas");
            double priceElectricity = expectedResult.getInt("priceElectricity");
            Assertions.assertEquals(priceColdWater, createBodyForRequestChangePrice().getPrice().getPriceColdWater());
            Assertions.assertEquals(priceHotWater, createBodyForRequestChangePrice().getPrice().getPriceHotWater());
            Assertions.assertEquals(priceGas, createBodyForRequestChangePrice().getPrice().getPriceGas());
            Assertions.assertEquals(priceElectricity, createBodyForRequestChangePrice().getPrice().getPriceElectricity());
        }
    }
}
