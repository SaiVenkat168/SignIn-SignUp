package com.example.loginactivity;

public class Users
{
    String username,email,userId,password;

    public Users(String username, String email, String userId, String password) {
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    public Users() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
