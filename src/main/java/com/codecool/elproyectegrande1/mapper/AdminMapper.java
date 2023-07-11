package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.admin.AdminDto;
import com.codecool.elproyectegrande1.entity.Admin;
import com.codecool.elproyectegrande1.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto mapEntityToAdminDto(Admin entity) {
        return new AdminDto(
                entity.getId(),
                entity.getNickname(),
                entity.getEmail(),
                entity.getUser()
        );
    }

    public Admin mapUserToAdmin(User user) {
        return new Admin(
                user.getUsername(),
                user.getEmail(),
                user
        );
    }
}