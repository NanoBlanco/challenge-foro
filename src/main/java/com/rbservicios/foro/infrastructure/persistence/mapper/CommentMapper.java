package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;

public class CommentMapper {

    public static CommentEntity fromDomain(Comment comment) {
        if (comment == null) return null;
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setContent(comment.getContent());
        entity.setUser(UserMapper.fromDomain(comment.getUser()));
        entity.setPost(PostMapper.fromDomainWithoutComments(comment.getPost()));
        return  entity;
    }

    public static Comment toDomain(CommentEntity entity) {
        if (entity == null) return null;
        Comment comment = new Comment();
        comment.setId(entity.getId());
        comment.setContent(entity.getContent());
        comment.setUser(UserMapper.toDomain(entity.getUser()));
        comment.setPost(PostMapper.toDomainWithoutComments(entity.getPost()));
        return comment;
    }
}
