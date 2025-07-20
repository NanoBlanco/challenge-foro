package com.rbservicios.foro.application.usescases.tag;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTagsUseCase {

    private final TagRepository repository;

    public ListTagsUseCase(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> execute() {
        return repository.findAll();
    }
}
