package com.rbservicios.foro.application.usescases.tag;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTagUseCase {

    private final TagRepository tagRepository;

    public CreateTagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Tag execute(Tag tag) {
        return tagRepository.save(tag);
    }
}
