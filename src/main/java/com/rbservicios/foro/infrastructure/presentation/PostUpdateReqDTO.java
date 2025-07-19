package com.rbservicios.foro.infrastructure.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PostUpdateReqDTO(
        @NotNull Long id,
        @NotBlank String title,
        @NotBlank String content,
        List<@NotNull Long> tagIds
) {
}
