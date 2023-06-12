package rest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private String USER_REGISTER_PATH = "/api/auth/register";
    private String USER_DELETE_PATH = "/api/auth/user";
    private String USER_LOGIN_PATH = "/api/auth/login";

    @Step("Создание тестового пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpecification())
                .body(user)
                .when()
                .post(USER_REGISTER_PATH)
                .then();
    }

    @Step("Получение токена авторизации тестового пользователя")
    public String getAuthToken(User user) {
        return given()
                .spec(getBaseSpecification())
                .body(user)
                .when()
                .post(USER_LOGIN_PATH)
                .then().extract().path("accessToken");
    }

    @Step("Удаление тестового пользователя")
    public ValidatableResponse delete(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpecification())
                .when()
                .delete(USER_DELETE_PATH)
                .then();
    }
}