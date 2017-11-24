package codesprinters.autotest.domain;

public class User {

    private String userName;
    private String password;

    public User(String name, String pass) {
        this.userName = name;
        this.password = pass;
    }

    public static User ADMIN = new User("autotestjava@gmail.com", "P@ssw0rd1");

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
