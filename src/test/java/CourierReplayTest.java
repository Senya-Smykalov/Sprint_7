import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierReplayTest {
    CourierClient courierClient = new CourierClient();

    public static int loginWithCourierId() {
        return CourierClient.loginCourierTrue();
    }

    public static void deleteCourierById(int courierId) {
        CourierClient.deleteCourierById(courierId);
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        CourierClient.createCourier();
    }

    @DisplayName("Негативная проверка - Создание дубля курьера")
    @Description("Попытка создания курьера с повторящимися данными")
    @Test
    public void createCourierWithoutRequiredFields() {
        CourierClient.courierReplay();
    }

    @After
    public void deleteCourier() {
        int courierId = loginWithCourierId();
        deleteCourierById(courierId);
    }

}

