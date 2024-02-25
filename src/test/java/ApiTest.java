import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.PlayerByIdModel;
import models.PlayerData;
import models.PlayerDelete;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import propertyReader.PropertyReader;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.patch;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})
public class ApiTest {

    private Properties properties;


    @BeforeClass
    public void setUp() {
        properties = PropertyReader.getProperties();
        RestAssured.baseURI = properties.getProperty("api.base.uri");
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
    }

    @Description("Get all users")
    @Severity(SeverityLevel.MINOR)
    @Feature("Какой функционал проверяю")
    @Story("User story example: Вход по логину и паролю")
    @Issue("Bug # 1")
    @TmsLink("TMS-456")
    @Test(enabled = false)
    public void test1() {
        List<PlayerData> list =
                given()
                        .when()
                        .get("get/all")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().jsonPath().getList("players", PlayerData.class);

        List<String> screenName =list.stream().map(PlayerData::getScreenName).collect(Collectors.toList());

        Assert.assertTrue(screenName.contains(properties.getProperty("api.screenName")));
    }

    @Description("Create user")
    @Severity(SeverityLevel.MINOR)
    @Feature("Создание пользователя")
    @Story("User story example: Вход по логину и паролю")
    @Issue("Bug # 1")
    @TmsLink("TMS-456")
    @Test(enabled = false)
    public void test2() {

            given()
                .queryParam("age", properties.getProperty("api.age"))
                .queryParam("gender", properties.getProperty("api.gender"))
                .queryParam("login", properties.getProperty("api.login"))
                .queryParam("password", properties.getProperty("api.password"))
                .queryParam("role", properties.getProperty("api.role"))
                .queryParam("screenName", properties.getProperty("api.screenName"))
                .when()
                .get("/create/" + properties.getProperty("api.supervisor.login"))
                .then()
                .statusCode(200);
    }

    @Description("Delete user")
    @Severity(SeverityLevel.MINOR)
    @Feature("Создание пользователя")
    @Story("User story example: Вход по логину и паролю")
    @Issue("Bug # 1")
    @TmsLink("TMS-456")
    @Test(enabled = false)
    public void test3() {

        PlayerDelete playerDelete = new PlayerDelete(447857369);
        given()
                .contentType(ContentType.JSON)
                .body(playerDelete)
                .pathParam("role", "supervisor")
                .delete("delete/{role}")
                .then().statusCode(204);
    }

    @Description("Get user on id")
    @Severity(SeverityLevel.MINOR)
    @Feature("Создание пользователя")
    @Story("User story example: Вход по логину и паролю")
    @Issue("Bug # 1")
    @TmsLink("TMS-456")
    @Test
    public void test4() {

        PlayerDelete playerDelete = new PlayerDelete(863154920);
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(playerDelete)
                .post("get");

        PlayerByIdModel player = response
                .then()
                .extract()
                .as(PlayerByIdModel.class);

        System.out.println(player.getId() + " ID");
        System.out.println(player.getLogin() + " LOGIN");
        System.out.println(player.getPassword() + " PASSWORD");
        System.out.println(player.getScreenName() + " SCREENNAME");
        System.out.println(player.getGender() + " GENDER");
        System.out.println(player.getAge() + " AGE");
        System.out.println(player.getRole() + " ROLE");

    }


}
