package com.rbservicios.foro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    private Long id;
    private String title;
    private String content;
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private LocalDateTime fechaPost;

    public Post(String title, String content, User user, List<Tag> tags, LocalDateTime fechaPost) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.tags = (tags != null) ? tags : new ArrayList<>();
        this.fechaPost = fechaPost;
    }
}
