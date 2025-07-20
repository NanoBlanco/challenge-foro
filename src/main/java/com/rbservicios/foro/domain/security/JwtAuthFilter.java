package com.rbservicios.foro.domain.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtUtils jwtUtil;

    public JwtAuthFilter(JwtUtils jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        log.info("Petición entrante a: {}", path);
        // Ignorar rutas públicas
        if (isPublicPath(path)) {
            log.info("Ruta pública detectada. Saltando filtro JWT.");
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader != null /*&& authorizationHeader.startsWith("Bearer ")*/) {
            String jwtToken = authorizationHeader.substring(7);
            try {
                DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);

                String username = jwtUtil.extractUserName(decodedJWT);
                String authoritiesString = jwtUtil.getSpecifiedClaim(decodedJWT, "authorities").asString();

                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString);

                SecurityContext context = SecurityContextHolder.getContext();
                var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                context.setAuthentication(authentication);

                SecurityContextHolder.setContext(context);
            } catch (JWTVerificationException ex) {
                //SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token inválido o expirado\"}");
                return;
            }
        }
        filterChain.doFilter(request, response);

    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/auth") || path.equals("/usuarios/registrar")
                || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui");
    }
}
