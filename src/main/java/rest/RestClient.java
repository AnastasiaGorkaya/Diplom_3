package rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private static String BASE_URI = "https://stellarburgers.nomoreparties.site";

    public static final RequestSpecification getBaseSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI).build();
    }
}