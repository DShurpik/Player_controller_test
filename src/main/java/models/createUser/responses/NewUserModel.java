package models.createUser.responses;

public class NewUserModel {
    private Integer id;
    private String login;
    private String password;
    private String screenName;
    private String gender;
    private Integer age;
    private String role;

    public NewUserModel(Integer id, String login, String password, String screenName, String gender, Integer age, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.screenName = screenName;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public NewUserModel() {}

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }
}
