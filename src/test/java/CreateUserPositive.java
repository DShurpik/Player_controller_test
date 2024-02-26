import baseTestPages.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.deleteUser.requests.PlayerDelete;
import models.createUser.requests.CreateNewUser;
import models.createUser.responses.NewUserModel;
import models.getAllPlayers.responses.GetAllUsers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import specifications.Specifications;

import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class CreateUserPositive extends BaseTest {

    private int userIdCreate;

    @AfterMethod
    public void tearDown() {
        PlayerDelete playerDelete = new PlayerDelete(userIdCreate, properties);
        playerDelete.deleteUser(playerDelete);
    }

    @Description("Creating a new user")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user has been created with valid values")
    @Story("Creating a new user")
    @Issue("Password must contain latin letters and numbers (min 7 max 15 characters) but it is possible to create with empty password field")
    @TmsLink("TMS-3")
    @Test()
    public void createNewUser() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification());

        CreateNewUser createNewUser = new CreateNewUser(properties);
        GetAllUsers getAllUsers = new GetAllUsers();

        Response response = createNewUser
                .createUserRequestSpecification()
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .when()
                .get("/create/{editor}");

        response.then().statusCode(200);

        NewUserModel newUserModel = response
                .then()
                .extract()
                .as(NewUserModel.class);

        List<NewUserModel> usersList = getAllUsers
                .getAllUsersList();

        List<String> screenName =usersList.stream().map(NewUserModel::getScreenName).collect(Collectors.toList());

        Assert.assertTrue(screenName.contains(properties.getProperty("api.create.screenName")));

        userIdCreate = newUserModel.getId();
    }
}
