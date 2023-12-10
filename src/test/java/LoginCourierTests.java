import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTests {
    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        createCourier();
    }

    @Test
    public void testCourierApi() {
        loginCourier();
        loginCourierFalse();
        TheCourierWhoExist();
    }

    @Step("Создание курьера для выполнения теста")
    private void createCourier() {
        courierClient.createCourier();
    }

    @Step("Авторизация курьера")
    private void loginCourier() {
        courierClient.loginCourierTrue();
    }

    @Step("Попытка авторизации с не корректными данными")
    private void loginCourierFalse() {
        courierClient.incorrectLogin();
    }

    @Step("Авторизация без обязательных полей")
    private void TheCourierWhoExist() {
        courierClient.loginWithoutfield();
    }

    @After
    public void deleteCourier() {
        int courierId = loginWithCourierId();
        deleteCourierById(courierId);
    }

    @Step("Авторизоваться за созданного клиента, для получения ID")
    public static int loginWithCourierId() {
        return CourierClient.loginCourierTrue();
    }

    @Step("Удалить курьера по полученному ID")
    public static void deleteCourierById(int courierId) {
        CourierClient.deleteCourierById(courierId);
    }

}
