package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.post.CreatePostUseCase;
import com.rbservicios.foro.application.usescases.post.ListPostUseCase;
import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import com.rbservicios.foro.infrastructure.persistence.mapper.PostDtoMapper;
import com.rbservicios.foro.infrastructure.persistence.mapper.PostMapper;
import com.rbservicios.foro.infrastructure.presentation.PostRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.PostResponseDTO;
import com.rbservicios.foro.infrastructure.presentation.PostWithCommentsDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final ListPostUseCase listPostUseCase;
    private final TagRepository tagRepository;

    public PostController(
            CreatePostUseCase createPostUseCase,
            ListPostUseCase listPostUseCase,
            TagRepository tagRepository) {
        this.createPostUseCase = createPostUseCase;
        this.listPostUseCase = listPostUseCase;
        this.tagRepository = tagRepository;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> creaPost(@RequestBody @Valid PostRequestDTO datos) {
        // Buscar tags por IDs
        List<Tag> tags = new ArrayList<>();
        if (datos.tagIds() != null && !datos.tagIds().isEmpty()) {
            tags = tagRepository.findAllById(datos.tagIds());
        }

        Post post = createPostUseCase.execute(
                datos.title(),
                datos.content(),
                datos.userId(),
                tags,
                LocalDateTime.now()
        );

        PostResponseDTO responseDTO = PostDtoMapper.toRespondDTO(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> listPosts() {
        List<Post> posts = listPostUseCase.execute();
        List<PostResponseDTO> response = posts.stream().map(PostDtoMapper::toRespondDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/with-comments")
    public ResponseEntity<List<PostWithCommentsDTO>> listPostsWithComments() {
        List<Post> posts = listPostUseCase.execute();
        List<PostWithCommentsDTO> response = posts.stream().map(PostDtoMapper::toPostWithCommentsDTO).toList();
        return ResponseEntity.ok(response);
    }
}
