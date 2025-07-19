package com.rbservicios.foro.infrastructure.persistence.repository;

import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.domain.repository.UserRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaUserRepository;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import com.rbservicios.foro.infrastructure.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository repository;

    public UserRepositoryImpl(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity entidad = UserMapper.fromDomain(user);
        entidad = repository.save(entidad);
        return UserMapper.toDomain(entidad);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByCorreo(String correo) {
        return repository.findByCorreo(correo).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(UserMapper::toDomain);
    }
}
