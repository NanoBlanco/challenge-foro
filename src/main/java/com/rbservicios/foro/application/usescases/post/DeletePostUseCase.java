package com.rbservicios.foro.application.usescases.post;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class DeletePostUseCase {

    private final PostRepository repository;

    public DeletePostUseCase(PostRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id, String username) throws AccessDeniedException {
        Post post = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        if (!post.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("No tiene permiso para eliminar este post");
        }
        repository.delete(post);
    }
}
