import data.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.example.OrdersClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrdersTest {
    private final Order order;
    Header header = new Header("Content-type", "application/json");

    public CreateOrdersTest(Order order) {
        this.order = order;
    }
  @Before
  public void setUp(){
      RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";

  }
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK"))},
                {new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("GREY"))},
                {new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of("BLACK", "GREY"))},
                {new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", List.of())},
        };
    }

    @Test
    @Step
    public void createOrders() {
        given()
                .header(header)
                .body(order)
                .post(OrdersClient.orderEndPoint)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

}
