package com.controlefrequencia.dto;

import com.controlefrequencia.enums.StatusMatricula;

public class MatriculaResponseDTO {

    private Long id;
    private Long periodoLetivoId;
    private String periodo;
    private Long disciplinaId;
    private String disciplina;
    private Integer limiteFaltas;
    private StatusMatricula status;

    public MatriculaResponseDTO(
            Long id,
            Long periodoLetivoId,
            String periodo,
            Long disciplinaId,
            String disciplina,
            Integer limiteFaltas,
            StatusMatricula status) {

        this.id = id;
        this.periodoLetivoId = periodoLetivoId;
        this.periodo = periodo;
        this.disciplinaId = disciplinaId;
        this.disciplina = disciplina;
        this.limiteFaltas = limiteFaltas;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getPeriodoLetivoId() {
        return periodoLetivoId;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Integer getLimiteFaltas() {
        return limiteFaltas;
    }

    public StatusMatricula getStatus() {
        return status;
    }
}