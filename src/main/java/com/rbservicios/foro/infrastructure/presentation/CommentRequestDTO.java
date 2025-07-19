package com.rbservicios.foro.infrastructure.presentation;

import java.time.LocalDateTime;

public record CommentRequestDTO(
        String content,
        Long userId,
        Long postId
) {
}
