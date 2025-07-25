package com.rbservicios.foro.infrastructure.presentation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long id,
        String content,
        String username,
        String title,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createAt
) {
}
