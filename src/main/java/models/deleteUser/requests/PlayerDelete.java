package models.deleteUser.requests;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class PlayerDelete {
    private Integer playerId;

    public PlayerDelete(Integer playerId) {
        this.playerId = playerId;
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
                .pathParam("role", "supervisor")
                .delete("delete/{role}")
                .then().statusCode(204);
    }
}
