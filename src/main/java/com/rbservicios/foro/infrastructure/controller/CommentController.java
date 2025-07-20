package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.comment.CreateCommentUseCase;
import com.rbservicios.foro.application.usescases.comment.ListCommentUseCase;
import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.mapper.CommentDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.CommentRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CreateCommentUseCase commentUseCase;
    private ListCommentUseCase listCommentUseCase;

    public CommentController(CreateCommentUseCase commentUseCase, ListCommentUseCase listCommentUseCase) {
        this.commentUseCase = commentUseCase;
        this.listCommentUseCase = listCommentUseCase;
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
}
