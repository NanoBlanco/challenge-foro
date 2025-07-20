package com.rbservicios.foro.infrastructure.presentation;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank String username,
        @NotBlank String clave
) {
}
