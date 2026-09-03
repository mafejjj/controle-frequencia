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

import com.controlefrequencia.dto.HorarioAulaRequestDTO;
import com.controlefrequencia.dto.HorarioAulaResponseDTO;
import com.controlefrequencia.service.HorarioAulaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/horarios")
public class HorarioAulaController {

    private final HorarioAulaService horarioService;

    public HorarioAulaController(HorarioAulaService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping
    public ResponseEntity<HorarioAulaResponseDTO> cadastrar(
            @Valid @RequestBody HorarioAulaRequestDTO dados) {

        HorarioAulaResponseDTO horario =
            horarioService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/horarios/" + horario.getId()))
            .body(horario);
    }

    @GetMapping("/periodo/{periodoLetivoId}")
    public ResponseEntity<List<HorarioAulaResponseDTO>>
            listarPorPeriodo(@PathVariable Long periodoLetivoId) {

        return ResponseEntity.ok(
            horarioService.listarPorPeriodo(periodoLetivoId)
        );
    }
}