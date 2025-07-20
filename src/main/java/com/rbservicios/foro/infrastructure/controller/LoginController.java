package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.user.LoginUseCase;
import com.rbservicios.foro.infrastructure.presentation.AuthRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO datos) {
        return new ResponseEntity<>(loginUseCase.loginUser(datos), HttpStatus.OK);
    }
}
