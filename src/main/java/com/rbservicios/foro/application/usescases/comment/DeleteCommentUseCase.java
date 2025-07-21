package com.rbservicios.foro.application.usescases.comment;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class DeleteCommentUseCase {

    private final CommentRepository repository;

    public DeleteCommentUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(Long id, String username) throws AccessDeniedException {
        Comment comment = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post no encontrado"));
        if (!comment.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("No tiene permiso para eliminar este comentario");
        }
        repository.delete(comment);
    }
}
