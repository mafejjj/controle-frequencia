package com.controlefrequencia.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAulaResponseDTO {

    private Long id;
    private Long matriculaId;
    private String disciplina;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer quantidadeAulas;

    public HorarioAulaResponseDTO(
            Long id,
            Long matriculaId,
            String disciplina,
            DayOfWeek diaSemana,
            LocalTime horaInicio,
            LocalTime horaFim,
            Integer quantidadeAulas) {

        this.id = id;
        this.matriculaId = matriculaId;
        this.disciplina = disciplina;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.quantidadeAulas = quantidadeAulas;
    }

    public Long getId() {
        return id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }
}