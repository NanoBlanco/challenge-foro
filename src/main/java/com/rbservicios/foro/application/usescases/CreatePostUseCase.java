package com.rbservicios.foro.application.usescases;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.PostRepository;
import com.rbservicios.foro.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreatePostUseCase {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CreatePostUseCase(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post execute(String title, String content, Long autorId, List<Tag> tags, LocalDateTime fecha) {
        var user = userRepository.findById(autorId).orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        var post = new Post(title, content, user, tags, fecha);
        return postRepository.save(post);
    }
}
