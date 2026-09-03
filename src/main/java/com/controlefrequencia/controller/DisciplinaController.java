package com.controlefrequencia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlefrequencia.dto.DisciplinaRequestDTO;
import com.controlefrequencia.dto.DisciplinaResponseDTO;
import com.controlefrequencia.service.DisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(
            DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> cadastrar(
            @Valid @RequestBody DisciplinaRequestDTO dados) {

        DisciplinaResponseDTO disciplina =
            disciplinaService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/disciplinas/" + disciplina.getId()))
            .body(disciplina);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DisciplinaResponseDTO>>
            listarPorUsuario(@PathVariable Long usuarioId) {

        return ResponseEntity.ok(
            disciplinaService.listarPorUsuario(usuarioId)
        );
    }
}