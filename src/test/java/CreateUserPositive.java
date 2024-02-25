import baseTestPages.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.getByPlayerId.responses.PlayerByIdModel;
import models.deleteUser.requests.PlayerDelete;
import models.createUser.requests.CreateNewUser;
import models.createUser.responses.NewUserModel;
import models.getAllPlayers.responses.GetAllUsers;
import models.getByPlayerId.requests.PlayerInfoByID;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import propertyReader.PropertyReader;
import specifications.Specifications;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class CreateUserPositive extends BaseTest {

    @AfterMethod
    public void tearDown() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification204());

        PlayerDelete playerDelete = new PlayerDelete(userId);
        playerDelete.deleteUser(playerDelete);

    }

    @Description("Get all users")
    @Severity(SeverityLevel.MINOR)
    @Feature("Checking showing all users")
    @Story("Checking that get request shows all users")
    @Issue("There is not issue")
    @TmsLink("TMS-1")
    @Test
    public void getAllUsersPositive() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        GetAllUsers getAllUsers = new GetAllUsers();

        List<NewUserModel> usersList = getAllUsers
                .getAllUsersList();

        Assert.assertEquals(usersList.size(), 9, "User list size " + usersList.size() + " does not equal expected result");
        log.info("User list size equals expected result 9");
    }

    @Description("Creating a new user")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user has been created with valid values")
    @Story("Creating a new user")
    @Issue("Password must contain latin letters and numbers (min 7 max 15 characters) but it is possible to create with empty password field")
    @TmsLink("TMS-3")
    @Test
    public void createNewUser() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification());

        CreateNewUser createNewUser = new CreateNewUser(properties);
        GetAllUsers getAllUsers = new GetAllUsers();

        Response response = createNewUser
                .createUserRequestSpecification()
                .when()
                .get("/create/" + properties.getProperty("api.supervisor.login"));

        response.then().statusCode(200);

        NewUserModel newUserModel = response
                .then()
                .extract()
                .as(NewUserModel.class);

        List<NewUserModel> usersList = getAllUsers
                .getAllUsersList();

        List<String> screenName =usersList.stream().map(NewUserModel::getScreenName).collect(Collectors.toList());

        Assert.assertTrue(screenName.contains(properties.getProperty("api.screenName")));

        userId = newUserModel.getId();
    }
}
