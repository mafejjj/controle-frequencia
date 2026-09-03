package com.controlefrequencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlefrequencia.entity.Disciplina;

public interface DisciplinaRepository
        extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByUsuario_IdOrderByNomeAsc(Long usuarioId);

    boolean existsByUsuario_IdAndNomeIgnoreCase(
        Long usuarioId,
        String nome
    );
}