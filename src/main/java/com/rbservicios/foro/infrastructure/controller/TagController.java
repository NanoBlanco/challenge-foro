package com.rbservicios.foro.infrastructure.controller;

import com.rbservicios.foro.application.usescases.CreateTagUseCase;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.infrastructure.persistence.mapper.TagDtoMapper;
import com.rbservicios.foro.infrastructure.presentation.TagRequestDTO;
import com.rbservicios.foro.infrastructure.presentation.TagResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TagController {

    private final CreateTagUseCase createTagUseCase;

    public TagController(CreateTagUseCase createTagUseCase) {
        this.createTagUseCase = createTagUseCase;
    }

    @PostMapping("/tags")
    public ResponseEntity<TagResponseDTO> crear(@RequestBody @Valid TagRequestDTO dto) {
        Tag tag = TagDtoMapper.toDomain(dto);
        Tag nuevo = createTagUseCase.execute(tag);
        TagResponseDTO respuesta = TagDtoMapper.toResponseDTO(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
