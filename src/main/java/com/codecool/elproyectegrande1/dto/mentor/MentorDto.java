package com.codecool.elproyectegrande1.dto.mentor;

import com.codecool.elproyectegrande1.entity.Image;
import com.codecool.elproyectegrande1.entity.Offer;
import com.codecool.elproyectegrande1.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MentorDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private int followers = 0;
    private List<Offer> offers = new ArrayList<>();

    private User user;
    private boolean isVerified = false;
    private Image profilePicture;
    private Set<String> followed;

    public MentorDto() {
    }

    public MentorDto(Long id, String nickname, String email, int followers, List<Offer> offers, User user, Set<String> followed) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.followers = followers;
        this.offers = offers;
        this.user = user;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<String> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<String> followed) {
        this.followed = followed;
    }
}
