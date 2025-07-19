package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostEntity fromDomain(Post post) {
        if (post == null) return null;
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());

        // User
        entity.setUser(UserMapper.fromDomain(post.getUser()));

        // Tags
        if (post.getTags() != null) {
            entity.setTags(
                    post.getTags().stream()
                            .map(TagMapper::fromDomain)
                            .collect(Collectors.toList())
            );
        }

        // Comments
        if (post.getComments() != null) {
            entity.setComments(
                    post.getComments().stream()
                            .map(CommentMapper::fromDomain)
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public static Post toDomain(PostEntity entity) {
        if (entity == null) return null;

        Post post = new Post();
        post.setId(entity.getId());
        post.setTitle(entity.getTitle());
        post.setContent(entity.getContent());

        // User
        post.setUser(UserMapper.toDomain(entity.getUser()));

        // Tags
        if (entity.getTags() != null) {
            post.setTags(
                    entity.getTags().stream()
                            .map(TagMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        // Comments
        if (entity.getComments() != null) {
            post.setComments(
                    entity.getComments().stream()
                            .map(CommentMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return post;
    }

    public static PostEntity fromDomainWithoutComments(Post post) {
        if (post == null) return null;
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        entity.setUser(UserMapper.fromDomain(post.getUser()));
        return entity;
    }

    public static Post toDomainWithoutComments(PostEntity entity) {
        if (entity == null) return null;
        Post post = new Post();
        post.setId(entity.getId());
        post.setTitle(entity.getTitle());
        post.setContent(entity.getContent());
        post.setUser(UserMapper.toDomain(entity.getUser()));
        return post;
    }

}
