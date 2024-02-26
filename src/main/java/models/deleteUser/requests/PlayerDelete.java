package models.deleteUser.requests;

import io.restassured.http.ContentType;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class PlayerDelete {
    private Integer playerId;
    private Properties properties;

    public PlayerDelete(Integer playerId, Properties properties) {
        this.playerId = playerId;
        this.properties = properties;
    }

    public PlayerDelete() {}

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public void deleteUser(PlayerDelete playerDelete) {
        given()
                .contentType(ContentType.JSON)
                .body(playerDelete)
                .pathParam("editor", properties.getProperty("api.supervisor.login"))
                .delete("delete/{editor}");
    }
}
