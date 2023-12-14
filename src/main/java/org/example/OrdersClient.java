package org.example;

import data.Order;
import io.qameta.allure.Step;
import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrdersClient {
    public static final String orderEndPoint = "/api/v1/orders";
    static Header header = new Header("Content-type", "application/json");

    @Step("Создание заказа с параметризацией")
    public static void createOrders(Order order) {
        given()
                .header(header)
                .body(order)
                .post(OrdersClient.orderEndPoint)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Step("Проверка получения списка заказов")
    public void getOrders() {
        given()
                .get(orderEndPoint)
                .then()
                .statusCode(200)
                .body("orders", not(empty()));
    }
}
