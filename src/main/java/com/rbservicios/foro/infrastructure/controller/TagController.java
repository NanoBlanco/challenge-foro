package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.tag.CreateTagUseCase;
import com.rbservicios.foro.application.usescases.tag.ListTagsUseCase;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.infrastructure.persistence.mapper.TagDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.TagRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.TagResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TagController {

    private final CreateTagUseCase createTagUseCase;
    private final ListTagsUseCase listTagsUseCase;

    public TagController(CreateTagUseCase createTagUseCase, ListTagsUseCase listTagsUseCase) {
        this.createTagUseCase = createTagUseCase;
        this.listTagsUseCase = listTagsUseCase;
    }

    @PostMapping("/tags")
    public ResponseEntity<TagResponseDTO> crear(@RequestBody @Valid TagRequestDTO dto) {
        Tag tag = TagDtoMapper.toDomain(dto);
        Tag nuevo = createTagUseCase.execute(tag);
        TagResponseDTO respuesta = TagDtoMapper.toResponseDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponseDTO>> listTags() {
        List<Tag> tags = listTagsUseCase.execute();
        List<TagResponseDTO> response = tags.stream().map(TagDtoMapper::toResponseDTO).toList();
        return ResponseEntity.ok(response);
    }
}
