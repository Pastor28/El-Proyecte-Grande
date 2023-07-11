package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.mentor.MentorDto;
import com.codecool.elproyectegrande1.entity.Mentor;
import com.codecool.elproyectegrande1.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MentorMapper {

    public MentorDto mapEntityToMentorDto(Mentor entity) {
        return new MentorDto(
            entity.getId(),
            entity.getNickname(),
            entity.getEmail(),
            entity.getFollowers(),
            entity.getOffers(),
            entity.getUser(),
            entity.getFollowed()
        );
    }

    public Mentor mapUserToMentor(User user) {
        return new Mentor(
                user.getUsername(),
                user.getEmail(),
                user,
                new HashSet<>()

        );
    }
}
