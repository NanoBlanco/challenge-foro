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
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createAt
) {
}
