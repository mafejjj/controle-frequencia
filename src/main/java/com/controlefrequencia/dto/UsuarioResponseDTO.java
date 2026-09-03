package com.controlefrequencia.dto;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String telefone;

    public UsuarioResponseDTO(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}