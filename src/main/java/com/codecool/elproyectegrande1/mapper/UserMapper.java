package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.user.UserDto;
import com.codecool.elproyectegrande1.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto mapEntityToDto(User user) {
        if(checkProfilePicture(user))
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getImageUrl(),
                    user.getProfilePicture().getId(),
                    user.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList()));

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getImageUrl(),
                null,
                user.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList()));
    }

    private boolean checkProfilePicture(User user) {
        return user.getProfilePicture() != null;
    }
}
