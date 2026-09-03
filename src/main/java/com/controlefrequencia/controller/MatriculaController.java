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

import com.controlefrequencia.dto.MatriculaRequestDTO;
import com.controlefrequencia.dto.MatriculaResponseDTO;
import com.controlefrequencia.service.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> cadastrar(
            @Valid @RequestBody MatriculaRequestDTO dados) {

        MatriculaResponseDTO matricula =
            matriculaService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/matriculas/" + matricula.getId()))
            .body(matricula);
    }

    @GetMapping("/periodo/{periodoLetivoId}")
    public ResponseEntity<List<MatriculaResponseDTO>>
            listarPorPeriodo(@PathVariable Long periodoLetivoId) {

        return ResponseEntity.ok(
            matriculaService.listarPorPeriodo(periodoLetivoId)
        );
    }
}