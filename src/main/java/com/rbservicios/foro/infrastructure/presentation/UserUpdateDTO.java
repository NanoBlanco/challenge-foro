package com.rbservicios.foro.infrastructure.presentation;

import com.rbservicios.foro.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotBlank String username,
        @NotBlank @Email String correo,
        @NotBlank String clave,
        @NotNull Role role

        ) {
}
