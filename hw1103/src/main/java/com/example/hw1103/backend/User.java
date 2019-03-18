package com.example.hw1103.backend;

import android.support.annotation.DrawableRes;

public class User {

    private String name;
    private String email;
    @DrawableRes
    private int icon;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
