package com.rbservicios.foro.domain.repository;


import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    List<Post> findByTag(Tag tag);
    List<Post> findByUser(User user);
}
