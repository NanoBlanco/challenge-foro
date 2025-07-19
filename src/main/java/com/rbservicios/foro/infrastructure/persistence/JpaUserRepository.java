package com.rbservicios.foro.infrastructure.persistence;

import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCorreo(String correo);
    Optional<UserEntity> findByUsername(String username);
}
