package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.mentor.MentorDto;
import com.codecool.elproyectegrande1.entity.Mentor;
import com.codecool.elproyectegrande1.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


class MentorMapperTest {

    private final MentorMapper mentorMapper = new MentorMapper();

    @Test
    void shouldMapEntityToMentorDto() {
        Mentor mentor = new Mentor("Test nickname", "Test email", new User(), new HashSet<>());
        mentor.setId(1L);
        mentor.setFollowers(10);

        MentorDto actual = mentorMapper.mapEntityToMentorDto(mentor);

        Assertions.assertEquals(mentor.getId(), actual.getId());
        Assertions.assertEquals(mentor.getNickname(), actual.getNickname());
        Assertions.assertEquals(mentor.getEmail(), actual.getEmail());
        Assertions.assertEquals(mentor.getOffers(), actual.getOffers());
        Assertions.assertEquals(mentor.getUser(), actual.getUser());
    }

    @Test
    void shouldMapUserToMentor() {
        User user = new User("Username", "email", "password");

        Mentor actual = mentorMapper.mapUserToMentor(user);

        Assertions.assertEquals(user.getUsername(), actual.getNickname());
        Assertions.assertEquals(user.getEmail(), actual.getEmail());
    }
}
