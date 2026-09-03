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

import com.controlefrequencia.dto.PeriodoLetivoRequestDTO;
import com.controlefrequencia.dto.PeriodoLetivoResponseDTO;
import com.controlefrequencia.service.PeriodoLetivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/periodos-letivos")
public class PeriodoLetivoController {

    private final PeriodoLetivoService periodoService;

    public PeriodoLetivoController(
            PeriodoLetivoService periodoService) {

        this.periodoService = periodoService;
    }

    @PostMapping
    public ResponseEntity<PeriodoLetivoResponseDTO> cadastrar(
            @Valid @RequestBody PeriodoLetivoRequestDTO dados) {

        PeriodoLetivoResponseDTO periodo =
            periodoService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/periodos-letivos/" + periodo.getId()))
            .body(periodo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PeriodoLetivoResponseDTO>>
            listarPorUsuario(@PathVariable Long usuarioId) {

        return ResponseEntity.ok(
            periodoService.listarPorUsuario(usuarioId)
        );
    }
}