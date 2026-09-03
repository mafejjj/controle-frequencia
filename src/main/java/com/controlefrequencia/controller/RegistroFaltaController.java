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

import com.controlefrequencia.dto.RegistroFaltaRequestDTO;
import com.controlefrequencia.dto.RegistroFaltaResponseDTO;
import com.controlefrequencia.service.RegistroFaltaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/faltas")
public class RegistroFaltaController {

    private final RegistroFaltaService faltaService;

    public RegistroFaltaController(
            RegistroFaltaService faltaService) {
        this.faltaService = faltaService;
    }

    @PostMapping
    public ResponseEntity<RegistroFaltaResponseDTO> cadastrar(
            @Valid @RequestBody RegistroFaltaRequestDTO dados) {

        RegistroFaltaResponseDTO registro =
            faltaService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/faltas/" + registro.getId()))
            .body(registro);
    }

    @GetMapping("/matricula/{matriculaId}")
    public ResponseEntity<List<RegistroFaltaResponseDTO>>
            listarPorMatricula(@PathVariable Long matriculaId) {

        return ResponseEntity.ok(
            faltaService.listarPorMatricula(matriculaId)
        );
    }
}