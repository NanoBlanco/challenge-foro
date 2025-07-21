package com.rbservicios.foro.application.usescases.post;

import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.PostRepository;
import com.rbservicios.foro.domain.repository.TagRepository;
import com.rbservicios.foro.domain.repository.UserRepository;
import com.rbservicios.foro.infrastructure.presentation.PostUpdateReqDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UpdatePostUseCase {

    private final PostRepository repository;
    private final TagRepository tagRepository;

    public UpdatePostUseCase(PostRepository repository, TagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    public void execute(PostUpdateReqDTO dato, String username) throws AccessDeniedException {
        var post = repository.findById(dato.id()).orElse(null);
        if (post != null) {
            if (!post.getUser().getUsername().equals(username)) {
                throw new AccessDeniedException("No tiene permiso para modificar este post");
            }

            post.setTitle(dato.title());
            post.setContent(dato.content());

            if (dato.tagIds() != null && !dato.tagIds().isEmpty()) {
                List<Tag> tags = tagRepository.findAllById(dato.tagIds());
                if (tags.size() != dato.tagIds().size()){
                    throw new IllegalArgumentException("Algunos tags no existen");
                }
                post.setTags(tags);
            } else {
                post.setTags(Collections.emptyList());
            }
            post.setCreateAt(LocalDateTime.now());
            repository.save(post);
        } else {
            throw new EntityNotFoundException("Post no encontrado");
        }
    }
}
