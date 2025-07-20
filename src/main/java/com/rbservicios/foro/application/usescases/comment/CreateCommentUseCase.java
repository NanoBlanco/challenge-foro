package com.rbservicios.foro.application.usescases.comment;

import com.rbservicios.foro.domain.model.Comment;
import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.domain.repository.CommentRepository;
import com.rbservicios.foro.domain.repository.PostRepository;
import com.rbservicios.foro.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateCommentUseCase {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CreateCommentUseCase(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Comment execute(Long userId, Long postId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + userId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post no encontrado con id: " + postId));

        Comment comment = new Comment(null, content, user, post, LocalDateTime.now());
        return commentRepository.save(comment);
    }
}
