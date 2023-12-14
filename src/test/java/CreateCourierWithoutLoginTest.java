import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierWithoutLoginTest {
    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @DisplayName("Негативная проверка - создание курьера без обязательного поля ")
    @Description("создание без обязательного поля")
    @Test
    public void createCourierWithoutRequiredFields() {
        CourierClient.createCourierWithoutLogin();
    }

}

