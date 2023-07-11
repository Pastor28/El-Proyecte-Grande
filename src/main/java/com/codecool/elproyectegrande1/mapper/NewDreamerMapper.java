package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.dreamer.DreamerDto;
import com.codecool.elproyectegrande1.dto.dreamer.NewDreamerDto;
import com.codecool.elproyectegrande1.entity.Dreamer;
import com.codecool.elproyectegrande1.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class NewDreamerMapper {
    public DreamerDto mapEntityToDreamerDto(Dreamer entity) {
        return new DreamerDto(
                entity.getId(),
                entity.getNickname(),
                entity.getEmail(),
                entity.getFollowers(),
                entity.getUser(),
                entity.getLetters(),
                entity.getDreams(),
                entity.getFollowed()
                );
    }

    public Dreamer mapNewDreamerDtoToEntity(NewDreamerDto dto) {
        return new Dreamer(dto.getNickname(), dto.getEmail(), dto.getUser(), dto.getLetters(), dto.getFollowed());
    }

    public Dreamer mapUserToDreamer(User user) {
        return new Dreamer(
                user.getUsername(),
                user.getEmail(),
                user,
                new HashSet<>(),
                new HashSet<>()
        );
    }
}