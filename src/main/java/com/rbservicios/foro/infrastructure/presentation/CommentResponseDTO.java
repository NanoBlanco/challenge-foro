package com.rbservicios.foro.infrastructure.presentation;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long id,
        String content,
        String userName,
        LocalDateTime fechaComentario
) {
}
