package com.codecool.elproyectegrande1.dto.user;

import com.codecool.elproyectegrande1.util.PasswordMatches;
import com.codecool.elproyectegrande1.util.ValidEmail;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@PasswordMatches
public class UserDto {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1)
    private String username;

    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private boolean isUsing2FA;
    private String profileImgUrl;
    private List<String> roles;

    private Long profilePictureId;

    public String getEmail() {
        return email;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }


    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDto(Long id, String username, String email, String profileImgUrl, Long profilePictureId, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.profilePictureId = profilePictureId;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

    public Long getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(Long profilePictureId) {
        this.profilePictureId = profilePictureId;
    }
}
