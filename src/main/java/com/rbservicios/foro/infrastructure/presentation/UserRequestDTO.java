package com.rbservicios.foro.infrastructure.presentation;

import com.rbservicios.foro.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank String username,
        @NotBlank @Email String correo,
        @NotBlank String clave
        ) {
}
