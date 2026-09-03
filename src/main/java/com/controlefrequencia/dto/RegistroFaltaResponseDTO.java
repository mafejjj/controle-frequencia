package com.controlefrequencia.dto;

import com.controlefrequencia.enums.TipoRegistroFalta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroFaltaResponseDTO {

    private Long id;
    private Long matriculaId;
    private String disciplina;
    private LocalDate data;
    private Integer quantidade;
    private TipoRegistroFalta tipo;
    private String observacao;
    private LocalDateTime registradoEm;

    public RegistroFaltaResponseDTO(
            Long id,
            Long matriculaId,
            String disciplina,
            LocalDate data,
            Integer quantidade,
            TipoRegistroFalta tipo,
            String observacao,
            LocalDateTime registradoEm) {

        this.id = id;
        this.matriculaId = matriculaId;
        this.disciplina = disciplina;
        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.observacao = observacao;
        this.registradoEm = registradoEm;
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

    public LocalDate getData() {
        return data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public TipoRegistroFalta getTipo() {
        return tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public LocalDateTime getRegistradoEm() {
        return registradoEm;
    }
}