package com.rbservicios.foro.infrastructure.presentation;

public record AuthResponseDTO(
        String username,
        String jwt,
        boolean status
) {
}
