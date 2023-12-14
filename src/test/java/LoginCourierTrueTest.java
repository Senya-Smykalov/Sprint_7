import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTrueTest {
    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        CourierClient.createCourier();// Предусловия - необходимо создать курьера
    }

    @Test
    public void loginCourierTrue() {
        CourierClient.loginCourierTrue();

    }

    @After
    public void deleteCourier() {
        int courierId = CourierClient.loginCourierTrue();
        CourierClient.deleteCourierById(courierId);
    }

}
