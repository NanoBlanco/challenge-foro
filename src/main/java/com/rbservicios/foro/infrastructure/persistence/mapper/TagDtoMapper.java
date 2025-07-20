package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.infrastructure.presentation.TagRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.TagResponseDTO;

import java.util.ArrayList;

public class TagDtoMapper {

    public static TagResponseDTO toResponseDTO(Tag tag) {
        if (tag == null) return null;

        return new TagResponseDTO(
                tag.getId(),
                tag.getNombre()
        );
    }

    public static Tag toDomain(TagRequestDTO dto) {
        if (dto == null) return null;

        Tag tag = new Tag();

        tag.setId(null);
        tag.setNombre(dto.nombre());
        tag.setPosts(new ArrayList<>());
        return tag;
    }
}
