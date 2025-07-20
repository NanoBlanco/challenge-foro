package com.rbservicios.foro.application.usescases.comment;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCommentUseCase {

    private final CommentRepository repository;


    public ListCommentUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> execute() {
        return repository.findAll();
    }
}
