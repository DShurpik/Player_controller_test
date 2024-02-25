package models;

import java.util.List;

public class PlayerData {
    private Integer id;
    private String screenName;
    private String gender;
    private Integer age;

    public PlayerData(Integer id, String screenName, String gender, Integer age) {
        this.id = id;
        this.screenName = screenName;
        this.gender = gender;
        this.age = age;
    }

    public PlayerData() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
