package com.controlefrequencia.dto;

import com.controlefrequencia.enums.StatusMatricula;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MatriculaRequestDTO {

    @NotNull
    private Long periodoLetivoId;

    @NotNull
    private Long disciplinaId;

    @NotNull
    @Min(value = 1, message = "O limite deve ser maior que zero")
    private Integer limiteFaltas;

    @NotNull
    private StatusMatricula status;

    public MatriculaRequestDTO() {
    }

    public Long getPeriodoLetivoId() {
        return periodoLetivoId;
    }

    public void setPeriodoLetivoId(Long periodoLetivoId) {
        this.periodoLetivoId = periodoLetivoId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Integer getLimiteFaltas() {
        return limiteFaltas;
    }

    public void setLimiteFaltas(Integer limiteFaltas) {
        this.limiteFaltas = limiteFaltas;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
}