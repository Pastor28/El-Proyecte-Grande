package com.codecool.elproyectegrande1.dto.admin;

import com.codecool.elproyectegrande1.entity.User;

public class AdminDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private User user;

    public AdminDto(Long id, String nickname, String email, User user) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.user = user;
    }

    public AdminDto() {
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
