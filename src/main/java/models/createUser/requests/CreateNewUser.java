package models.createUser.requests;

import io.restassured.specification.RequestSpecification;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CreateNewUser {

    private int age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public CreateNewUser(Properties properties) {
        this.age = Integer.parseInt(properties.getProperty("api.create.age"));
        this.gender = properties.getProperty("api.create.gender");
        this.login = properties.getProperty("api.create.login");
        this.password = properties.getProperty("api.create.password");
        this.role = properties.getProperty("api.create.role");
        this.screenName = properties.getProperty("api.create.screenName");
    }


    public RequestSpecification createUserRequestSpecification() {
        return given()
                .queryParam("age", age)
                .queryParam("gender", gender)
                .queryParam("login", login)
                .queryParam("password", password)
                .queryParam("role", role)
                .queryParam("screenName", screenName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
