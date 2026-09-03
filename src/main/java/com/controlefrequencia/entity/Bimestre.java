package com.controlefrequencia.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "bimestres",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_bimestre_periodo_numero",
            columnNames = {"periodo_letivo_id", "numero"}
        )
    }
)
public class Bimestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodo_letivo_id", nullable = false)
    private PeriodoLetivo periodoLetivo;

    public Bimestre() {
    }

    public Bimestre(
            Integer numero,
            LocalDate dataInicio,
            LocalDate dataFim,
            PeriodoLetivo periodoLetivo) {

        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.periodoLetivo = periodoLetivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PeriodoLetivo getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }
}