package com.example.kelompok_8.login.register;

import com.google.gson.annotations.SerializedName;
public class RegisterRequest {
    @SerializedName("username")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
