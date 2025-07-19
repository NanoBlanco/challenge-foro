package com.rbservicios.foro.domain.repository;

import com.rbservicios.foro.domain.model.Tag;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);
    Optional<Tag> findById(Long id);
    List<Tag> findAll();
    List<Tag> findAllById(List<@NotNull Long> ids);
}
