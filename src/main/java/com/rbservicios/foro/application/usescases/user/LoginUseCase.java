package com.rbservicios.foro.application.usescases.user;

import com.rbservicios.foro.domain.security.CustomUserDetailsService;
import com.rbservicios.foro.domain.security.JwtUtils;
import com.rbservicios.foro.infrastructure.presentation.AuthRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final JwtUtils jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;

    public LoginUseCase(CustomUserDetailsService userDetailsService, JwtUtils jwtUtil, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO loginUser(AuthRequestDTO datos) {
        var username = datos.username();
        var password = datos.clave();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtil.generaToken(authentication);
        return new AuthResponseDTO(username,  accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetail = userDetailsService.loadUserByUsername(username);
        if(userDetail == null) {
            throw new BadCredentialsException("Usuario inválido o clave errónea");
        }
        if(!passwordEncoder.matches(password, userDetail.getPassword())) {
            throw new BadCredentialsException("Usuario inválido o clave errónea");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
    }
}
