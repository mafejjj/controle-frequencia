package com.controlefrequencia.entity;

import com.controlefrequencia.enums.StatusMatricula;

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
    name = "matriculas",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_matricula_periodo_disciplina",
            columnNames = {"periodo_letivo_id", "disciplina_id"}
        )
    }
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limite_faltas", nullable = false)
    private Integer limiteFaltas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMatricula status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodo_letivo_id", nullable = false)
    private PeriodoLetivo periodoLetivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    public Matricula() {
    }

    public Matricula(
            Integer limiteFaltas,
            StatusMatricula status,
            PeriodoLetivo periodoLetivo,
            Disciplina disciplina) {

        this.limiteFaltas = limiteFaltas;
        this.status = status;
        this.periodoLetivo = periodoLetivo;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
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

    public PeriodoLetivo getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}