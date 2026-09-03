package com.controlefrequencia.dto;

import java.time.LocalDate;

import com.controlefrequencia.enums.StatusPeriodo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PeriodoLetivoRequestDTO {

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "O nome do período é obrigatório")
    @Size(max = 20)
    private String nome;

    @NotNull(message = "O número do semestre é obrigatório")
    @Min(value = 1, message = "O semestre deve ser maior que zero")
    @Max(value = 20, message = "O semestre deve ser menor ou igual a 20")
    private Integer numeroSemestre;

    @NotNull(message = "A data inicial é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data final é obrigatória")
    private LocalDate dataFim;

    @NotNull(message = "O status é obrigatório")
    private StatusPeriodo status;

    public PeriodoLetivoRequestDTO() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(Integer numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
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

    public StatusPeriodo getStatus() {
        return status;
    }

    public void setStatus(StatusPeriodo status) {
        this.status = status;
    }
}