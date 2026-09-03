package com.controlefrequencia.entity;

import com.controlefrequencia.enums.TipoRegistroFalta;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "registros_falta",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_falta_matricula_data",
            columnNames = {"matricula_id", "data"}
        )
    }
)
public class RegistroFalta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoRegistroFalta tipo;

    @Column(length = 250)
    private String observacao;

    @Column(name = "registrado_em", nullable = false, updatable = false)
    private LocalDateTime registradoEm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    public RegistroFalta() {
    }

    public RegistroFalta(
            LocalDate data,
            Integer quantidade,
            TipoRegistroFalta tipo,
            String observacao,
            Matricula matricula) {

        this.data = data;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.observacao = observacao;
        this.matricula = matricula;
    }

    @PrePersist
    public void antesDeSalvar() {
        registradoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public void setTipo(TipoRegistroFalta tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public LocalDateTime getRegistradoEm() {
        return registradoEm;
    }

    public Matricula getMatricula() {
        return matricula;
    }
}