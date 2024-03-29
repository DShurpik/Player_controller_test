import baseTestPages.BaseTest;
import helpers.IntRandom;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.createUser.requests.CreateNewUser;
import models.deleteUser.requests.PlayerDelete;
import models.getAllPlayers.requests.PlayerHeader;
import models.getByPlayerId.requests.PlayerInfoByID;
import models.updatePlayer.requests.UpdateUserModel;
import models.updatePlayer.responses.UpdateUserResponse;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})
public class NegativeTests extends BaseTest {

    @Description("Get all users with bad header")
    @Severity(SeverityLevel.MINOR)
    @Feature("Checking that is not possible to get users list with bad headers")
    @Story("Get request with bad header")
    @Issue("There is a bad value in header")
    @TmsLink("TMS-2")
    @Test
    public void getAllUsersNegative() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification());

        PlayerHeader playerHeader = new PlayerHeader(properties);

        given()
                .when()
                .header(playerHeader.getKeyName(), playerHeader.getKeyValue())
                .get("get/all")
                .then()
                .statusCode(406);
    }

    @Description("Delete non-create user on id")
    @Severity(SeverityLevel.MINOR)
    @Feature("Checking that status code after deleting non-create user is 204")
    @Story("Delete non-create user on id")
    @Issue("There is a status code 403, but there must be 204 No content")
    @TmsLink("TMS-4")
    @Test
    public void deleteNonCreateUserOnId() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification403());

        IntRandom intRandom = new IntRandom();
        int randomValue = intRandom.generateRandomNumber();

        PlayerDelete playerDelete = new PlayerDelete(randomValue, properties);

        given()
                .contentType(ContentType.JSON)
                .body(playerDelete)
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .delete("delete/{editor}");
    }

    @Description("Creating a new user with age equals 16")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user has been created with valid values")
    @Story("Creating a new user")
    @Issue("It is not possible to create a new user with age equals 16")
    @TmsLink("TMS-7")
    @Test
    public void createNewUserNegative() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification400());

        CreateNewUser createNewUser = new CreateNewUser(properties);

        createNewUser
                .createUserRequestSpecification()
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .when()
                .get("/create/{editor}");
    }

    @Description("Getting a user info by random user ID")
    @Severity(SeverityLevel.NORMAL)
    @Feature("")
    @Story("Getting a user info by user ID")
    @Issue("Get request returns 200 status code and empty body")
    @TmsLink("TMS-8")
    @Test
    public void getUserInfoByIdNegativeTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        IntRandom intRandom = new IntRandom();
        int randomId = intRandom.generateRandomNumber();

        PlayerInfoByID playerInfoByID = new PlayerInfoByID(randomId);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(playerInfoByID)
                .post("get");

        response.then().extract().body().asString();
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }

    @Description("Updating user info with random ID or non-created user")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user info has been patched")
    @Story("Patching user info")
    @Issue("Using non-create user id, random integer, but status code is 200 BUG!")
    @TmsLink("TMS-8")
    @Test
    public void updatePlayerInfoNegative() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        UpdateUserModel updateUserModel = new UpdateUserModel();

        IntRandom intRandom = new IntRandom();

        given()
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .pathParam("id", intRandom.generateRandomNumber())
                .body(updateUserModel)
                .when()
                .patch("update/{editor}/{id}");
    }
}
