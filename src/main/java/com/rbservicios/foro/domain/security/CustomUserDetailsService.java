package com.rbservicios.foro.domain.security;

import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.repository.UserRepositoryImpl;
import com.rbservicios.foro.infrastructure.presentation.AuthRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.AuthResponseDTO;
import com.rbservicios.foro.infrastructure.presentation.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passEncoder;

    private final UserRepositoryImpl userRepository;
    private final JwtUtils jwtUtil;

    public CustomUserDetailsService(UserRepositoryImpl userRepository, JwtUtils jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getClave(), // contraseña
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
