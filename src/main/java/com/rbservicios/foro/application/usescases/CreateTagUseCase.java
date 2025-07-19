package com.rbservicios.foro.application.usescases;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTagUseCase {

    private final TagRepository tagRepository;

    public CreateTagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag execute(Tag tag) {
        return tagRepository.save(tag);
    }
}
