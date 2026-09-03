package com.controlefrequencia.dto;

public class DisciplinaResponseDTO {

    private Long id;
    private Long usuarioId;
    private String nome;
    private String codigo;

    public DisciplinaResponseDTO(
            Long id,
            Long usuarioId,
            String nome,
            String codigo) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }
}