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

import com.controlefrequencia.dto.BimestreRequestDTO;
import com.controlefrequencia.dto.BimestreResponseDTO;
import com.controlefrequencia.service.BimestreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bimestres")
public class BimestreController {

    private final BimestreService bimestreService;

    public BimestreController(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }

    @PostMapping
    public ResponseEntity<BimestreResponseDTO> cadastrar(
            @Valid @RequestBody BimestreRequestDTO dados) {

        BimestreResponseDTO bimestre =
            bimestreService.cadastrar(dados);

        return ResponseEntity
            .created(URI.create("/bimestres/" + bimestre.getId()))
            .body(bimestre);
    }

    @GetMapping("/periodo/{periodoLetivoId}")
    public ResponseEntity<List<BimestreResponseDTO>>
            listarPorPeriodo(@PathVariable Long periodoLetivoId) {

        return ResponseEntity.ok(
            bimestreService.listarPorPeriodo(periodoLetivoId)
        );
    }
}