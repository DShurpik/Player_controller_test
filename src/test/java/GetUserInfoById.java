import baseTestPages.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.createUser.requests.CreateNewUser;
import models.createUser.responses.NewUserModel;
import models.getByPlayerId.requests.PlayerInfoByID;
import models.getByPlayerId.responses.PlayerByIdModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import propertyReader.PropertyReader;
import specifications.Specifications;

import java.util.Properties;

import static io.restassured.RestAssured.given;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class GetUserInfoById extends BaseTest {

    @BeforeMethod
    public void createUser() {
        CreateNewUser createNewUser = new CreateNewUser(properties);

        NewUserModel newUserModel = createNewUser
                .createUserRequestSpecification()
                .when()
                .get("/create/" + properties.getProperty("api.supervisor.login"))
                .then()
                .extract()
                .as(NewUserModel.class);

        userId = newUserModel.getId();
    }

    @Description("Getting a new user by user ID")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user has been created with valid values")
    @Story("Getting a new user by user ID")
    @Issue("")
    @TmsLink("TMS-8")
    @Test
    public void getPlayerByPlayerId() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        PlayerInfoByID playerInfoByID = new PlayerInfoByID(userId);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(playerInfoByID)
                .post("get");

        PlayerByIdModel player = response
                .then()
                .extract()
                .as(PlayerByIdModel.class);

        Assert.assertEquals(player.getLogin(), properties.getProperty("api.login"));
    }
}
