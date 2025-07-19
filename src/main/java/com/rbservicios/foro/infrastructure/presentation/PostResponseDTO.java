package com.rbservicios.foro.infrastructure.presentation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
        Long id,
        String title,
        String content,
        String userName,
        List<String> tags,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaPost
) {
}
