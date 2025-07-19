package com.rbservicios.foro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag {

    private Long id;
    private String nombre;
    private List<Post> posts;
}
