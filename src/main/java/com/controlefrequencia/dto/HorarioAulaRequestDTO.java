package com.controlefrequencia.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class HorarioAulaRequestDTO {

    @NotNull
    private Long matriculaId;

    @NotNull
    private DayOfWeek diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;

    @NotNull
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    private Integer quantidadeAulas;

    public HorarioAulaRequestDTO() {
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(Integer quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
    }
}