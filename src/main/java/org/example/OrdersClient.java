package org.example;

import io.restassured.http.Header;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class OrdersClient {
    public static final String orderEndPoint = "/api/v1/orders";
    Header header = new Header("Content-type", "application/json");

    public void getOrders() {
        given()
                .get(orderEndPoint)
                .then()
                .statusCode(200)
                .body("orders", not(empty()));
    }
}
