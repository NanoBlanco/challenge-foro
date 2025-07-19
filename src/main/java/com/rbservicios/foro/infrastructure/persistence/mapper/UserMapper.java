package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity fromDomain(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setCorreo(user.getCorreo());
        entity.setClave(user.getClave());
        entity.setRole(user.getRole());
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setCorreo(entity.getCorreo());
        user.setClave(entity.getClave());
        user.setRole(entity.getRole());

        return user;
    }
}
