package com.controlefrequencia.entity;

import java.time.LocalDate;

import com.controlefrequencia.enums.StatusPeriodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    name = "periodos_letivos",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_periodo_usuario_nome",
            columnNames = {"usuario_id", "nome"}
        )
    }
)
public class PeriodoLetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(name = "numero_semestre", nullable = false)
    private Integer numeroSemestre;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPeriodo status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public PeriodoLetivo() {
    }

    public PeriodoLetivo(
        String nome,
        Integer numeroSemestre,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusPeriodo status,
        Usuario usuario) {

        this.nome = nome;
        this.numeroSemestre = numeroSemestre;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.usuario = usuario;
    }

    public Integer getNumeroSemestre() {
    return numeroSemestre;
    }

    public void setNumeroSemestre(Integer numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}