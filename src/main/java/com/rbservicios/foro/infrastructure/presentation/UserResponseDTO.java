package com.rbservicios.foro.infrastructure.presentation;

import com.rbservicios.foro.domain.model.Role;

public record UserResponseDTO (
        Long id,
        String username,
        String correo,
        Role role
) {
}
