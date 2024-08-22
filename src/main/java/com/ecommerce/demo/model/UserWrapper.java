package com.ecommerce.demo.model;

public class UserWrapper {
    private String username;
    private String  password;

    public UserWrapper(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
