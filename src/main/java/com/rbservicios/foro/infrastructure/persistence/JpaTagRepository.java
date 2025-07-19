package com.rbservicios.foro.infrastructure.persistence;

import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagRepository extends JpaRepository<TagEntity, Long> {
}
