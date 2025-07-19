package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import com.rbservicios.foro.infrastructure.presentation.CommentRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.CommentResponseDTO;

import java.time.LocalDateTime;

public class CommentDtoMapper {

    public static CommentResponseDTO toRespondDTO(Comment comment) {
        if (comment == null) return null;

        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                comment.getUser() != null ? comment.getUser().getUsername() : null,
                comment.getPost() != null ? comment.getPost().getTitle() : null,
                comment.getCreateAt()
        );
    }

    public static CommentEntity fromRequestDto(CommentRequestDTO datos, UserEntity user, PostEntity post) {
        if (datos == null) return null;

        var entity = new CommentEntity();
        entity.setId(null);
        entity.setContent(datos.content());
        entity.setUser(user);
        entity.setPost(post);
        entity.setCreateAt(LocalDateTime.now());
        return entity;
    }
}
