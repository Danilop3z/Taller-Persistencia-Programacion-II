package model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID= 1L;
    private String userName;
    private String password;

    // Constructor
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
