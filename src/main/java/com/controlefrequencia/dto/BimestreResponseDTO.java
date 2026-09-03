package com.controlefrequencia.dto;

import java.time.LocalDate;

public class BimestreResponseDTO {

    private Long id;
    private Long periodoLetivoId;
    private Integer numero;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public BimestreResponseDTO(
            Long id,
            Long periodoLetivoId,
            Integer numero,
            LocalDate dataInicio,
            LocalDate dataFim) {

        this.id = id;
        this.periodoLetivoId = periodoLetivoId;
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public Long getPeriodoLetivoId() {
        return periodoLetivoId;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
}