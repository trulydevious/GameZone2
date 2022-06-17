package com.example.gamezone;

import java.io.Serializable;

public class Friend implements Serializable {

    private int id_user;
    private String email;

    public Friend(int id_user, String email) {
        this.id_user = id_user;
        this.email = email;
    }

    public Friend() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id_user=" + id_user +
                ", email='" + email + '\'' +
                '}';
    }
}
