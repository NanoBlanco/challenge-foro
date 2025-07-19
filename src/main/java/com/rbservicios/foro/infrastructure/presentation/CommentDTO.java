package com.rbservicios.foro.infrastructure.presentation;

import java.time.LocalDateTime;

public record CommentDTO(
        String content,
        UserRequestDTO usuario,
        LocalDateTime fechaComentario,
        PostRequestDTO post
) {
}
