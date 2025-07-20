package com.rbservicios.foro.application.usescases.post;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPostUseCase {

    private final PostRepository repository;

    public ListPostUseCase(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute() {
        return repository.findAll();
    }
}
