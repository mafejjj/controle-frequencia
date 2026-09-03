package com.controlefrequencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlefrequencia.entity.Matricula;

public interface MatriculaRepository
        extends JpaRepository<Matricula, Long> {

    List<Matricula>
        findByPeriodoLetivo_IdOrderByDisciplina_NomeAsc(
            Long periodoLetivoId
        );

    boolean existsByPeriodoLetivo_IdAndDisciplina_Id(
        Long periodoLetivoId,
        Long disciplinaId
    );
}