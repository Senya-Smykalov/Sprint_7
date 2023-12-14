package org.example;

import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class CourierClient {
    private static final String baseURI = "http://qa-scooter.praktikum-services.ru/";
    private static final String createCourierEndPoint = "api/v1/courier";
    private static final String loginCourierEndPoint = "/api/v1/courier/login";
    private static final String deleteCourierEndPoint = "/api/v1/courier/:id";
    static Header header = new Header("Content-type", "application/json");
    private static Response response;
    private static int courierId;

    @Step("Позитивная проверка - создание курьера")
    public static void createCourier() {
        File createCourier = new File("src/test/resources/createCourier.json");
        given()
                .header(header)
                .body(createCourier)
                .when()
                .post(createCourierEndPoint)
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Step("Негативная проверка - создание курьера без обязательного поля")
    public static void createCourierWithoutLogin() {
        File badCreateCourier = new File("src/test/resources/badCreateCourier.json");
        given()
                .header(header)
                .body(badCreateCourier)
                .when()
                .post(createCourierEndPoint)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Негативеная проверка - попытка создание курьера с повторяющимися данными")
    public static void courierReplay() {
        File replayCreateCourier = new File("src/test/resources/createCourier.json");
        given()
                .header(header)
                .body(replayCreateCourier)
                .when()
                .post(createCourierEndPoint)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Позитивная проверка - логин курьера в системе")
    public static int loginCourierTrue() {
        File loginCourierTrue = new File("src/test/resources/loginCourier.json");
        Response response = given()
                .header(header)
                .body(loginCourierTrue)
                .when()
                .post(loginCourierEndPoint)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .response();

        return response.path("id");
    }

    @Step("Негативная проверка - логин курьера в системе без обязательного поля")
    public static void loginWithoutfield() {
        File loginWithoutLoginfield = new File("src/test/resources/badLoginOne.json");
        given()
                .header(header)
                .body(loginWithoutLoginfield)
                .when()
                .post(loginCourierEndPoint)
                .then()
                .statusCode(504);
    }

    @Step("Негативная проверка - попытка входа в систему под несущствующим логином")
    public static void incorrectLogin() {
        File IncorrectLogin = new File("src/test/resources/incorrectLogin.json");
        given()
                .header(header)
                .body(IncorrectLogin)
                .when()
                .post(loginCourierEndPoint)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Удаление курьера по ID")
    public static void deleteCourierById(int courierId) {
        given()
                .pathParam("id", courierId)
                .when()
                .delete("/api/v1/courier/{id}")
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }

}

