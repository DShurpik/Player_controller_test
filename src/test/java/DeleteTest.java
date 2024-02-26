import baseTestPages.BaseTest;
import io.qameta.allure.*;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.createUser.requests.CreateNewUser;
import models.createUser.requests.CreateNewUserBeforeTest;
import models.createUser.responses.NewUserModel;
import models.deleteUser.requests.PlayerDelete;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import specifications.Specifications;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class DeleteTest extends BaseTest {

    private static int userId;

    @BeforeMethod
    public void createUser() {
        CreateNewUserBeforeTest createNewUser = new CreateNewUserBeforeTest(properties);

        NewUserModel newUserModel = createNewUser
                .createUserRequestSpecification()
                .when()
                .get("/create/" + properties.getProperty("api.supervisor.login"))
                .then()
                .extract()
                .as(NewUserModel.class);

        userId = newUserModel.getId();
    }

    @Description("Creating a new user")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Checking that user has been created with valid values")
    @Story("Creating a new user")
    @Issue("Password must contain latin letters and numbers (min 7 max 15 characters) but it is possible to create with empty password field")
    @TmsLink("TMS-6")
    @Test
    public void deleteUserPositiveTest() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification());

        PlayerDelete playerDelete = new PlayerDelete(userId, properties);
        playerDelete.deleteUser(playerDelete);
    }
}
