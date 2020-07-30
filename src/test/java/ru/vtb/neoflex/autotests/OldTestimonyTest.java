package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;

public class OldTestimonyTest {

    @Test
    public void test() {

        String oldTestimonyURI = "http://localhost:8080/services/testimony/get/old/testimony/02-2020";

        int actualStatusCode = RequestTestController.getRequestCodeForOldTestimony(oldTestimonyURI);
        Assertions.assertEquals(200, actualStatusCode);
    }
}
