package models.updatePlayer.requests;

import java.util.Properties;

public class UpdateUserModel {
    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public UpdateUserModel(Integer age, String gender, String login, String password, String role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public UpdateUserModel(Properties properties) {
        this.age = Integer.parseInt(properties.getProperty("api.patch.age"));
        this.gender = properties.getProperty("api.patch.gender");
        this.login = properties.getProperty("api.patch.login");
        this.password = properties.getProperty("api.patch.password");
        this.role = properties.getProperty("api.patch.role");
        this.screenName = properties.getProperty("api.patch.screenName");
    }

    public UpdateUserModel() {}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
