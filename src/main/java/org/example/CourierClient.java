package org.example;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class CourierClient {
    private static final String baseURI = "http://qa-scooter.praktikum-services.ru/";
    private static final String createCourierEndPoint = "api/v1/courier";
    private static final String loginCourierEndPoint = "/api/v1/courier/login";
    private static final String deleteCourierEndPoint = "/api/v1/courier/:id";
    private static Response response;
    private static int courierId;
    static Header header = new Header("Content-type", "application/json");

    @Test
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

