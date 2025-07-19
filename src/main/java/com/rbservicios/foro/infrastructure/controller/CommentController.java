package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.CreateCommentUseCase;
import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.mapper.CommentDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.CommentRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CreateCommentUseCase commentUseCase;

    public CommentController(CreateCommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDTO> crear(@RequestBody @Valid CommentRequestDTO dto) {
        Comment comment = commentUseCase.execute(dto.userId(), dto.postId(), dto.content());
        CommentResponseDTO respuesta = CommentDtoMapper.toRespondDTO(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

}
