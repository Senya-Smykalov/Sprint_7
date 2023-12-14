import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierFalseTest {
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
        CourierClient.createCourier();// Предусловия - необходимо создать курьера
    }

    @Test
    public void loginCourierFalse() {
        courierClient.incorrectLogin();
    }

    @After
    public void deleteCourier() {
        int courierId = loginWithCourierId();
        deleteCourierById(courierId);
    }

}

