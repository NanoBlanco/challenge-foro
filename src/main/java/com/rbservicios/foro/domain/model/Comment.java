package com.rbservicios.foro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    private Long id;
    private String content;
    private LocalDateTime fechaComentario;
    private User user;
    private  Post post;
}
