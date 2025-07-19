package com.rbservicios.foro.infrastructure.persistence.entity;

import com.rbservicios.foro.domain.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String correo;
    private String clave;
    @Enumerated(EnumType.STRING)
    private Role role;

}
