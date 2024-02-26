import baseTestPages.BaseTest;
import io.qameta.allure.*;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import models.createUser.responses.NewUserModel;
import models.getAllPlayers.responses.GetAllUsers;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import specifications.Specifications;

import java.util.List;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class GetAllUsersPositive extends BaseTest {

    @Description("Get all users")
    @Severity(SeverityLevel.MINOR)
    @Feature("Checking showing all users")
    @Story("Checking that get request shows all users")
    @Issue("There is not issue")
    @TmsLink("TMS-1")
    @Test(priority = 1)
    public void getAllUsersPositive() {
        Specifications.installSpecifications(Specifications.requestSpecification(),
                Specifications.responseSpecification200());

        GetAllUsers getAllUsers = new GetAllUsers();

        List<NewUserModel> usersList = getAllUsers
                .getAllUsersList();

        Assert.assertEquals(usersList.size(), 6, "User list size " + usersList.size() + " does not equal expected result");
        log.info("User list size equals expected result 6");
    }
}
