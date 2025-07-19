package com.rbservicios.foro.infrastructure.persistence;

import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByTags_Id(Long tagId);
    List<PostEntity> findByUser_Id(Long userId);
}
