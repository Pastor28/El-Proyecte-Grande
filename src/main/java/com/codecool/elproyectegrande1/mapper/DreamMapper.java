package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.dream.DreamDto;
import com.codecool.elproyectegrande1.dto.dream.NewDreamDto;
import com.codecool.elproyectegrande1.entity.Dream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DreamMapper {

    @Autowired
    private CommentMapper commentMapper;

    public DreamDto mapEntityToDreamDto(Dream entity) {
        if (entity.getMainImage() == null)
            return new DreamDto(
                    entity.getId(),
                    entity.getDreamTitle(),
                    entity.getDreamDescription(),
                    entity.getHashtags(),
                    null,
                    entity.getComments()
                            .stream()
                            .map(commentMapper::mapEntityToCommentDto)
                            .collect(Collectors.toSet()),
                    entity.getLikes(),
                    entity.getViews()
            );
        return new DreamDto(
                entity.getId(),
                entity.getDreamTitle(),
                entity.getDreamDescription(),
                entity.getHashtags(),
                entity.getMainImage().getId(),
                entity.getComments()
                        .stream()
                        .map(commentMapper::mapEntityToCommentDto)
                        .collect(Collectors.toSet()),
                entity.getLikes(),
                entity.getViews()
        );
    }

    public Dream mapNewDreamDtoToEntity(NewDreamDto dto) {
        return new Dream(
                dto.getDreamTitle(),
                dto.getDreamDescription(),
                dto.getHashtags(),
                dto.getImage()
        );
    }
}