import baseTestPages.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.createUser.requests.CreateNewUser;
import models.createUser.requests.CreateNewUserBeforeTest;
import models.createUser.responses.NewUserModel;
import models.deleteUser.requests.PlayerDelete;
import models.updatePlayer.requests.UpdateUserModel;
import models.updatePlayer.responses.UpdateUserResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class UpdateUserInfo extends BaseTest {

    private int userIdUpdate;

    @BeforeMethod
    public void createUser() {
        CreateNewUserBeforeTest createNewUser = new CreateNewUserBeforeTest(properties);

        NewUserModel newUserModel = createNewUser
                .createUserRequestSpecification()
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .when()
                .get("/create/{editor}")
                .then()
                .extract()
                .as(NewUserModel.class);

        userIdUpdate = newUserModel.getId();
    }

    @AfterMethod
    public void tearDown() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification204());

        PlayerDelete playerDelete = new PlayerDelete(userIdUpdate, properties);
        playerDelete.deleteUser(playerDelete);
    }

    @Description("Updating user info")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user info has been patched")
    @Story("Pathcing user info")
    @Issue("")
    @TmsLink("TMS-8")
    @Test(priority = 5)
    public void updatePlayerInfoPositive() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        UpdateUserModel updateUserModel = new UpdateUserModel(properties);

        Response response = given()
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .pathParam("id", userIdUpdate)
                .body(updateUserModel)
                .when()
                .patch("update/{editor}/{id}");

        UpdateUserResponse updateUserResponse = response
                .then()
                .extract()
                .as(UpdateUserResponse.class);

        Assert.assertEquals(updateUserResponse.getLogin(), "user_test");

        userIdUpdate = updateUserResponse.getId();
    }
}
