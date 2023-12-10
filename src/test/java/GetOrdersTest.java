import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.OrdersClient;
import org.junit.Before;
import org.junit.Test;

public class GetOrdersTest {
    OrdersClient ordersClient = new OrdersClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @Test
    @Step("Получение списка заказов")
    public void getOrders() {
        ordersClient.getOrders();
    }

}
