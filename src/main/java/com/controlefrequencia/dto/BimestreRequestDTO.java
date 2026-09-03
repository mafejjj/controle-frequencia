package com.controlefrequencia.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BimestreRequestDTO {

    @NotNull(message = "O período letivo é obrigatório")
    private Long periodoLetivoId;

    @NotNull(message = "O número do bimestre é obrigatório")
    @Min(value = 1, message = "O número mínimo é 1")
    @Max(value = 4, message = "O número máximo é 4")
    private Integer numero;

    @NotNull(message = "A data inicial é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data final é obrigatória")
    private LocalDate dataFim;

    public BimestreRequestDTO() {
    }

    public Long getPeriodoLetivoId() {
        return periodoLetivoId;
    }

    public void setPeriodoLetivoId(Long periodoLetivoId) {
        this.periodoLetivoId = periodoLetivoId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}