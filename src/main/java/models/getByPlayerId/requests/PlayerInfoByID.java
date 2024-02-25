package models.getByPlayerId.requests;

public class PlayerInfoByID {
    private Integer playerId;

    public PlayerInfoByID(Integer playerId) {
        this.playerId = playerId;
    }

    public PlayerInfoByID() {}

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
