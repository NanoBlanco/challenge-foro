package com.rbservicios.foro.infrastructure.persistence.repository;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.repository.CommentRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaCommentRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaPostRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaUserRepository;
import com.rbservicios.foro.infrastructure.persistence.entity.CommentEntity;
import com.rbservicios.foro.infrastructure.persistence.mapper.CommentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository repository;
    private final JpaUserRepository userRepository;
    private final JpaPostRepository postRepository;

    public CommentRepositoryImpl(
            JpaCommentRepository repository,
            JpaUserRepository userRepository,
            JpaPostRepository postRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entidad = CommentMapper.fromDomain(comment);
        entidad = repository.save(entidad);
        entidad.setUser(userRepository.findById(entidad.getUser().getId()).orElse(null));
        entidad.setPost(postRepository.findById(entidad.getPost().getId()).orElse(null));
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

    @Override
    public List<Comment> findAll() {
        return repository.findAll().stream().map(CommentMapper::toDomain).toList();
    }
}
