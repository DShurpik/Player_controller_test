package models;

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
}
