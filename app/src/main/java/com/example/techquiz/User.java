package com.example.techquiz;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private Integer score;

    // userId

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // username

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // password


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // score


    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
