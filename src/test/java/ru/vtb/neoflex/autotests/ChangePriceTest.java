package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.model.Price;
import ru.neoflex.model.RequestChangePrice;

public class ChangePriceTest {

    @Test
    public void checkCodeSuccessForChangePriceTest() {
        String changePriceURI = "http://localhost:8080/services/testimony/changePrice";
        RequestChangePrice requestChangePrice = new RequestChangePrice();
        Price price = new Price();

        price.setPriceColdWater(1050);
        price.setPriceElectricity(123);
        price.setPriceGas(124);
        price.setPriceElectricity(12412412);
        requestChangePrice.setPrice(price);

        int actualStatusCode = RequestTestController.getRequestCodeForChangePrice(changePriceURI, requestChangePrice);

        Assertions.assertEquals(200, actualStatusCode);
    }
}
