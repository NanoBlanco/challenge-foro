package com.rbservicios.foro.application.usescases.user;

import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.domain.repository.UserRepository;
import com.rbservicios.foro.infrastructure.presentation.AuthResponseDTO;
import com.rbservicios.foro.infrastructure.presentation.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserUseCase {

    @Autowired
    PasswordEncoder passEncoder;
    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User execute(User user) {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está registrado");
        }
        if (repository.findByCorreo(user.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        user.setClave(passEncoder.encode(user.getClave()));
        return repository.save(user);
    }

}
