package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.model.Price;
import ru.neoflex.model.RequestChangePrice;
import ru.neoflex.model.ResponseChangePrice;

public class ChangePriceTest {
    String changePriceURI = "http://localhost:8080/services/testimony/changePrice";

    @Test
    public void checkCodeSuccessForChangePriceTest() {
        RequestChangePrice requestChangePrice = new RequestChangePrice();
        Price price = new Price();

        price.setPriceColdWater(1050);
        price.setPriceHotWater(123);
        price.setPriceGas(124);
        price.setPriceElectricity(12412412);
        requestChangePrice.setPrice(price);

        int actualStatusCode = RequestTestController.getRequestCodeForChangePrice(changePriceURI, requestChangePrice);

        Assertions.assertEquals(200, actualStatusCode);
    }


    @Test
    public void checkFaultCodeSuccessForChangePriceTest() {
        RequestChangePrice requestChangePrice = new RequestChangePrice();
        Price price = new Price();

        price.setPriceColdWater(1050);
        price.setPriceHotWater(123);
        price.setPriceGas(124);
        price.setPriceElectricity(12412412);
        requestChangePrice.setPrice(price);

        ResponseChangePrice responseChangePrice = RequestTestController.getResponseBodyForChangePrice(changePriceURI, requestChangePrice);
        String resultCode = responseChangePrice.getResultCode();
        String resultText = responseChangePrice.getResultText();

        Assertions.assertEquals("0", resultCode);
        Assertions.assertEquals("success", resultText);
    }
}
