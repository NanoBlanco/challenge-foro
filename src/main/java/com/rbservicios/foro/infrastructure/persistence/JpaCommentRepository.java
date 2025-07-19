package com.rbservicios.foro.infrastructure.persistence;

import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPost_Id(Long id);
    List<CommentEntity> findByUser_Id(Long id);
}
