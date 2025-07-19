package com.rbservicios.foro.domain.repository;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findByPost_Id(Long id);
    List<Comment> findByUser_Id(Long id);
}
