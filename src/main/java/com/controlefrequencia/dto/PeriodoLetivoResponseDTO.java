package com.controlefrequencia.dto;

import java.time.LocalDate;

import com.controlefrequencia.enums.StatusPeriodo;

public class PeriodoLetivoResponseDTO {

    private Long id;
    private Long usuarioId;
    private String nome;
    private Integer numeroSemestre;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusPeriodo status;

    public PeriodoLetivoResponseDTO(
            Long id,
            Long usuarioId,
            String nome,
            Integer numeroSemestre,
            LocalDate dataInicio,
            LocalDate dataFim,
            StatusPeriodo status) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.numeroSemestre = numeroSemestre;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumeroSemestre() {
        return numeroSemestre;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public StatusPeriodo getStatus() {
        return status;
    }
}