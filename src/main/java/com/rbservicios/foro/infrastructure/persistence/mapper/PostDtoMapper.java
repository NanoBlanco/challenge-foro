package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import com.rbservicios.foro.infrastructure.presentation.PostRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.PostResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    public static PostResponseDTO toRespondDTO(PostEntity entity) {
        if (entity == null) return null;

        return new PostResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUser() != null ? entity.getUser().getUsername() : null,
                entity.getTags() != null
                        ? entity.getTags().stream()
                        .map(TagEntity::getNombre)
                        .collect(Collectors.toList())
                        : Collections.emptyList(),
                entity.getCreatedAt()
        );
    }

    public static PostEntity fromRequestDTO(PostRequestDTO dto, UserEntity user, List<TagEntity> tags) {
        if (dto == null) return null;

        PostEntity entity = new PostEntity();
        entity.setTitle(dto.title());
        entity.setContent(dto.content());
        entity.setUser(user);
        entity.setTags(tags);

        return entity;
    }
}
