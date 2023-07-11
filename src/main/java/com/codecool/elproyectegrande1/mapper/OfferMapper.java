package com.codecool.elproyectegrande1.mapper;

import com.codecool.elproyectegrande1.dto.offer.NewOfferDto;
import com.codecool.elproyectegrande1.dto.offer.OfferDto;
import com.codecool.elproyectegrande1.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OfferMapper {
    @Autowired
    private CommentMapper commentMapper;

    public OfferDto mapEntityToOfferDto(Offer entity) {
        if (entity.getMainImage() == null)
            return new OfferDto(
                    entity.getId(),
                    entity.getType(),
                    entity.getTitle(),
                    entity.getDescription(),
                    entity.getPrice(),
                    entity.getDate(),
                    null,
                    entity.getLikes(),
                    entity.getViews(),
                    entity.getComments()
                            .stream()
                            .map(commentMapper::mapEntityToCommentDto)
                            .collect(Collectors.toSet())
            );

        return new OfferDto(
                entity.getId(),
                entity.getType(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDate(),
                entity.getMainImage().getId(),
                entity.getLikes(),
                entity.getViews(),
                entity.getComments()
                .stream()
                .map(commentMapper::mapEntityToCommentDto)
                .collect(Collectors.toSet())
        );
    }

    public Offer mapOfferDtoToEntity(NewOfferDto dto) {
        return new Offer(
                dto.getType(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getDate(),
                dto.getImage()
        );
    }
}
