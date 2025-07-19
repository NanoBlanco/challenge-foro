package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;

public class TagMapper {

    public static TagEntity fromDomain(Tag tag) {
        if (tag == null) return null;
        TagEntity entity = new TagEntity();
        entity.setId(tag.getId());
        entity.setNombre(tag.getNombre());

        return entity;
    }

    public static Tag toDomain(TagEntity entity) {
        if (entity == null) return null;
        Tag tag = new Tag();
        tag.setId(entity.getId());
        tag.setNombre(entity.getNombre());

        return tag;
    }
}
