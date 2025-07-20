package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.user.CreateUserUseCase;
import com.rbservicios.foro.domain.model.Role;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.mapper.UserDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.UserRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final CreateUserUseCase userUseCase;

    public UserController(CreateUserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UserResponseDTO> creaUsuario(@RequestBody @Valid UserRequestDTO datos) {
        var user = new User(null, datos.username(), datos.correo(), datos.clave(), Role.USER);
        var nuevo = userUseCase.execute(user);
        var respuesta = UserDtoMapper.toRespondDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

}
