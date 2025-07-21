package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.post.CreatePostUseCase;
import com.rbservicios.foro.application.usescases.post.DeletePostUseCase;
import com.rbservicios.foro.application.usescases.post.ListPostUseCase;
import com.rbservicios.foro.application.usescases.post.UpdatePostUseCase;
import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.repository.TagRepository;
import com.rbservicios.foro.infrastructure.persistence.mapper.PostDtoMapper;
import com.rbservicios.foro.infrastructure.persistence.mapper.PostMapper;
import com.rbservicios.foro.infrastructure.presentation.PostRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.PostResponseDTO;
import com.rbservicios.foro.infrastructure.presentation.PostUpdateReqDTO;
import com.rbservicios.foro.infrastructure.presentation.PostWithCommentsDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final ListPostUseCase listPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final TagRepository tagRepository;

    public PostController(
            CreatePostUseCase createPostUseCase,
            ListPostUseCase listPostUseCase,
            UpdatePostUseCase updatePostUseCase,
            DeletePostUseCase deletePostUseCase,
            TagRepository tagRepository) {
        this.createPostUseCase = createPostUseCase;
        this.listPostUseCase = listPostUseCase;
        this.updatePostUseCase = updatePostUseCase;
        this.deletePostUseCase = deletePostUseCase;
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

    @PutMapping("/posts")
    public ResponseEntity<Void> updatePost(@RequestBody @Valid PostUpdateReqDTO dato) throws AccessDeniedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        updatePostUseCase.execute(dato, username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws AccessDeniedException {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        deletePostUseCase.execute(id, username);
        return ResponseEntity.noContent().build();
    }
}
