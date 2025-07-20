package com.rbservicios.foro.infrastructure.persistence.mapper;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.UserEntity;
import com.rbservicios.foro.infrastructure.presentation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    public static PostResponseDTO toRespondDTO(Post entity) {
        if (entity == null) return null;

        return new PostResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getUser() != null ? entity.getUser().getUsername() : null,
                entity.getTags() != null
                        ? entity.getTags().stream()
                        .map(Tag::getNombre)
                        .toList()
                        : Collections.emptyList(),
                entity.getCreateAt()
        );
    }

    public static Post fromRequestDTO(PostRequestDTO dto, User user, List<Tag> tags) {
        if (dto == null) return null;

        Post entity = new Post();
        entity.setTitle(dto.title());
        entity.setContent(dto.content());
        entity.setUser(user);
        entity.setTags(tags);
        entity.setCreateAt(LocalDateTime.now());
        return entity;
    }

    public static PostWithCommentsDTO toPostWithCommentsDTO(Post post) {
        if (post == null) return null;

        List<CommentSummaryDTO> comments = post.getComments() != null
                ? post.getComments().stream().map(comment ->
                new CommentSummaryDTO(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser() != null ? comment.getUser().getUsername() : null,
                        comment.getCreateAt()
                )
        ).toList()
                : Collections.emptyList();

        return new PostWithCommentsDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser() != null ? post.getUser().getUsername() : null,
                post.getTags() != null
                        ? post.getTags().stream().map(Tag::getNombre).toList()
                        : Collections.emptyList(),
                post.getCreateAt(),
                comments
        );
    }

}
