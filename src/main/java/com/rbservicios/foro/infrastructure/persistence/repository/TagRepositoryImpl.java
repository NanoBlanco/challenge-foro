package com.rbservicios.foro.infrastructure.persistence.repository;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaTagRepository;
import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;
import com.rbservicios.foro.infrastructure.persistence.mapper.TagMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final JpaTagRepository repository;

    public TagRepositoryImpl(JpaTagRepository repository){
        this.repository = repository;
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity entity = TagMapper.fromDomain(tag);
        entity = repository.save(entity);
        return TagMapper.toDomain(entity);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return repository.findById(id).map(TagMapper::toDomain);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll().stream().map(TagMapper::toDomain).toList();
    }

    @Override
    public List<Tag> findAllById(List<@NotNull Long> ids) {
        return repository.findAllById(ids).stream().map(TagMapper::toDomain).toList();
    }
}
