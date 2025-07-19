package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;

public class CommentMapper {

    public static CommentEntity fromDomain(Comment comment) {
        if (comment == null) return null;
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setContent(comment.getContent());
        if (comment.getUser() != null && comment.getUser().getId() != null){
            UserEntity userEntity = new UserEntity();
            userEntity.setId(comment.getUser().getId());
            entity.setUser(userEntity);

        }
        if (comment.getPost() != null && comment.getPost().getId() != null) {
            PostEntity postEntity = new PostEntity();
            postEntity.setId(comment.getPost().getId());
            entity.setPost(postEntity);
        }
        entity.setCreateAt(comment.getCreateAt());
        return  entity;
    }

    public static Comment toDomain(CommentEntity entity) {
        if (entity == null) return null;
        Comment comment = new Comment();
        comment.setId(entity.getId());
        comment.setContent(entity.getContent());
        comment.setUser(UserMapper.toDomain(entity.getUser()));
        comment.setPost(PostMapper.toDomainWithoutComments(entity.getPost()));
        comment.setCreateAt(entity.getCreateAt());
        return comment;
    }
}
