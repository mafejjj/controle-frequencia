package com.controlefrequencia.dto;

import com.controlefrequencia.enums.TipoRegistroFalta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RegistroFaltaRequestDTO {

    @NotNull
    private Long matriculaId;

    @NotNull
    private LocalDate data;

    @NotNull
    private TipoRegistroFalta tipo;

    @Size(max = 250)
    private String observacao;

    public RegistroFaltaRequestDTO() {
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoRegistroFalta getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegistroFalta tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}