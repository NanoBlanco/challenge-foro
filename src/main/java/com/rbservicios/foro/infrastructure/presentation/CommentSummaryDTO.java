package com.rbservicios.foro.infrastructure.presentation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CommentSummaryDTO(
        Long id,
        String content,
        String username,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createAt
) {
}
