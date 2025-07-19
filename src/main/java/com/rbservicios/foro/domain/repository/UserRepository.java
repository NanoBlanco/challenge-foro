package com.rbservicios.foro.domain.repository;

import com.rbservicios.foro.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByCorreo(String correo);
    Optional<User> findByUsername(String username);
}
