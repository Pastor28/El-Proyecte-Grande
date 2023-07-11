package com.codecool.elproyectegrande1.dto.dreamer;

import com.codecool.elproyectegrande1.entity.Dream;
import com.codecool.elproyectegrande1.entity.Image;
import com.codecool.elproyectegrande1.entity.Letter;
import com.codecool.elproyectegrande1.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DreamerDto {
    private Long id;
    private String nickname;
    private String email;
    private int followers = 0;
    private BigDecimal funds;
    private User user;
    private Set<Letter> letters;
    private Image profilePicture;
    private List<Dream> dreams = new ArrayList<>();
    private Set<String> followed;

    public DreamerDto() {
    }

    public DreamerDto(Long id, String nickname, String email, int followers, User user, Set<Letter> letters, List<Dream> dreams, Set<String> followed) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.followers = followers;
        this.funds = new BigDecimal(0);
        this.user = user;
        this.letters = letters;
        this.dreams = dreams;
        this.followed = followed;
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

    public int getFollowers() {
        return followers;
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


    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Letter> getLetters() {
        return letters;
    }

    public void setLetters(Set<Letter> letters) {
        this.letters = letters;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Dream> getDreams() {
        return dreams;
    }

    public void setDreams(List<Dream> dreams) {
        this.dreams = dreams;
    }

    public Set<String> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<String> followed) {
        this.followed = followed;
    }
}
