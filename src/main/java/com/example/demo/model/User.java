package com.example.demo.model;
import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 263434701950670170L;

    private int userId;

    private String userName;

    private String pwd;

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return String.format("user_id: %d, user_name: %s, pwd: %s", userId, userName, pwd);
    }
}
