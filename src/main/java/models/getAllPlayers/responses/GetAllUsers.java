package models.getAllPlayers.responses;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import models.createUser.responses.NewUserModel;
import models.getAllPlayers.requests.PlayerHeader;

import java.util.List;

import static io.restassured.RestAssured.given;

@Log4j
public class GetAllUsers {

    public List<NewUserModel> getAllUsersList(PlayerHeader playerHeader) {

        Response response = given()
                .when()
                .header(playerHeader.getKeyName(), playerHeader.getKeyValue())
                .get("get/all");

        return response
                .then()
                .extract()
                .body().jsonPath().getList("players", NewUserModel.class);
    }

    public List<NewUserModel> getAllUsersList() {

        Response response = given()
                .when()
                .get("get/all");

        return response
                .then()
                .extract()
                .body().jsonPath().getList("players", NewUserModel.class);
    }
}
