package com.rbservicios.foro.infrastructure.presentation;

import java.time.LocalDateTime;
import java.util.List;

public record PostWithCommentsDTO(
        Long id,
        String title,
        String content,
        String userName,
        List<String> tags,
        LocalDateTime fechaPost,
        List<CommentResponseDTO> comments
) {
}
