import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.CourierClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTests {
    CourierClient courierClient = new CourierClient();
    private static String baseURI = "http://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        RestAssured.baseURI = baseURI;
    }


    @DisplayName("Тест - проверка создания курьера ")
    @Description("Список проверок : создание курьера(успешное), попытка создания дубля, создание без обязательного поля")
    @Test
    public void testsCreateCourier() {
        createCourier();
        createCourierWithoutLogin();
        duplicateCourier();
    }

    @Step("создание курьера")
    public void createCourier() {
        CourierClient.createCourier();
    }

    @Step("Создание курьера без обязательного поля")
    public void createCourierWithoutLogin() {
        CourierClient.createCourierWithoutLogin();
    }

    @Step("Создание курьера с повторяющимися данными")
    public void duplicateCourier() {
        CourierClient.courierReplay();
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
