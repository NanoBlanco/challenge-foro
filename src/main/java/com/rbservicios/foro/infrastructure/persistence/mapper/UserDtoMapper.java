package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Role;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import com.rbservicios.foro.infrastructure.presentation.UserRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.UserResponseDTO;

public class UserDtoMapper {

    public static UserResponseDTO toRespondDTO(User user) {
        if (user == null) return null;

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getCorreo(),
                user.getRole()
        );
    }

    public static UserEntity fromRequestDTO(UserRequestDTO datos) {
        if (datos == null) return null;

        var entity = new UserEntity();
        entity.setId(null);
        entity.setUsername(datos.username());
        entity.setCorreo(datos.correo());
        entity.setClave(datos.clave());
        entity.setRole(datos.role());
        return entity;
    }
}
