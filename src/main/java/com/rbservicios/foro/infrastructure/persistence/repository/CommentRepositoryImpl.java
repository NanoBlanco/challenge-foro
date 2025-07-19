package com.rbservicios.foro.infrastructure.persistence.repository;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.repository.CommentRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaCommentRepository;
import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;
import com.rbservicios.foro.infrastructure.persistence.mapper.CommentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository repository;

    public CommentRepositoryImpl(JpaCommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entidad = CommentMapper.fromDomain(comment);
        entidad = repository.save(entidad);
        return CommentMapper.toDomain(entidad);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return repository.findById(id).map(CommentMapper::toDomain);
    }

    @Override
    public List<Comment> findByPost_Id(Long id) {
        return repository.findByPost_Id(id).stream().map(CommentMapper::toDomain).toList();
    }

    @Override
    public List<Comment> findByUser_Id(Long id) {
        return repository.findByUser_Id(id).stream().map(CommentMapper::toDomain).toList();
    }
}
