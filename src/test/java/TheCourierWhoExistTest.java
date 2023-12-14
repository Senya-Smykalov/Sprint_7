import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.Before;
import org.junit.Test;

public class TheCourierWhoExistTest {

    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void theCourierWhoExist() {
        courierClient.loginWithoutfield();
    }
}
