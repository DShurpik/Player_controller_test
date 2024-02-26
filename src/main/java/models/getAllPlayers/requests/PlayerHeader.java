package models.getAllPlayers.requests;

import java.util.Properties;

public class PlayerHeader {
    private String keyName;
    private String keyValue;

    public PlayerHeader(Properties properties) {
        this.keyName = properties.getProperty("api.accept");
        this.keyValue = properties.getProperty("api.valueAccept");
    }

    public PlayerHeader() {}

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
