package ru.neoflex.controllers;

import io.restassured.http.ContentType;
import ru.neoflex.model.RequestChangePrice;
import ru.neoflex.model.RequestSaveTestimony;

import static io.restassured.RestAssured.given;

public class RequestTestController {

    public static int getRequestCode(String url, RequestSaveTestimony requestSaveTestimony) {
        return given().
                contentType(ContentType.JSON).
                body(requestSaveTestimony).
                when().
                post(url).
                then().
                extract().
                response().
                getStatusCode();
    }

    public static int getRequestCodeForChangePrice(String url, RequestChangePrice requestChangePrice) {
        return given().
                contentType(ContentType.JSON).
                body(requestChangePrice).
                when().
                post(url).
                then().
                extract().
                response().
                getStatusCode();
    }

    public static int getRequestCodeForOldTestimony(String url) {
        return given().
                get(url).
                then().
                extract().
                response().
                getStatusCode();
    }
}
