package com.rbservicios.foro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private Long id;
    private String username;
    private String correo;
    private String clave;
    private Role role;

}
