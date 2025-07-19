package com.rbservicios.foro.infrastructure.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PostRequestDTO(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull Long userId,
        List<@NotNull Long> tagIds
) {
}
