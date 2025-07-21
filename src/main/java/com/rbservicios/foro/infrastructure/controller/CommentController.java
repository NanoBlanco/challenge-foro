package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.comment.CreateCommentUseCase;
import com.rbservicios.foro.application.usescases.comment.DeleteCommentUseCase;
import com.rbservicios.foro.application.usescases.comment.ListCommentUseCase;
import com.rbservicios.foro.application.usescases.comment.UpdateCommentUseCase;
import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.mapper.CommentDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.CommentRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.CommentResponseDTO;
import com.rbservicios.foro.infrastructure.presentation.CommentUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private final CreateCommentUseCase commentUseCase;
    private final ListCommentUseCase listCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;

    public CommentController(
            CreateCommentUseCase commentUseCase,
            ListCommentUseCase listCommentUseCase,
            UpdateCommentUseCase updateCommentUseCase,
            DeleteCommentUseCase deleteCommentUseCase) {
        this.commentUseCase = commentUseCase;
        this.listCommentUseCase = listCommentUseCase;
        this.updateCommentUseCase = updateCommentUseCase;
        this.deleteCommentUseCase = deleteCommentUseCase;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDTO> crear(@RequestBody @Valid CommentRequestDTO dto) {
        Comment comment = commentUseCase.execute(dto.userId(), dto.postId(), dto.content());
        CommentResponseDTO respuesta = CommentDtoMapper.toRespondDTO(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDTO>> listAllComment() {
        List<Comment> comments = listCommentUseCase.execute();
        List<CommentResponseDTO> response = comments.stream().map(CommentDtoMapper::toRespondDTO).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/comments")
    public ResponseEntity<Void> actualizaComment(@RequestBody @Valid CommentUpdateDTO dato) throws AccessDeniedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        updateCommentUseCase.execute(dato, username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws AccessDeniedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        deleteCommentUseCase.execute(id, username);
        return ResponseEntity.noContent().build();
    }
}
