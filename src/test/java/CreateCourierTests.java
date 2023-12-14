import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTests {
    private static String baseURI = "http://qa-scooter.praktikum-services.ru/";
    CourierClient courierClient = new CourierClient();

    public static int loginWithCourierId() {
        return CourierClient.loginCourierTrue();
    }

    public static void deleteCourierById(int courierId) {
        CourierClient.deleteCourierById(courierId);
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = baseURI;
    }

    @DisplayName("Тест - проверка создания курьера ")
    @Description("Список проверок : создание курьера(успешное)")
    @Test

    public void createCourier() {
        CourierClient.createCourier();
    }

    @After
    public void deleteCourier() {
        int courierId = loginWithCourierId();
        deleteCourierById(courierId);
    }

}
