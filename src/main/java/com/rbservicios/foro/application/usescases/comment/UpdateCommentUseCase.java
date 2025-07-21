package com.rbservicios.foro.application.usescases.comment;

import com.rbservicios.foro.domain.repository.CommentRepository;
import com.rbservicios.foro.infrastructure.presentation.CommentUpdateDTO;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@Service
public class UpdateCommentUseCase {

    private final CommentRepository repository;

    public UpdateCommentUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public void execute(CommentUpdateDTO dato, String username) throws AccessDeniedException {
        var comment = repository.findById(dato.id()).orElse(null);
        if (comment != null) {
            if (!comment.getUser().getUsername().equals(username)) {
                throw new AccessDeniedException("No tiene permiso para modificar este comentario");
            }

            comment.setContent(dato.content());
            comment.setCreateAt(LocalDateTime.now());
            repository.save(comment);
        } else {
            throw new EntityNotFoundException("Comentario no encontrado");
        }
    }
}
