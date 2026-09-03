package com.controlefrequencia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlefrequencia.entity.PeriodoLetivo;
import com.controlefrequencia.enums.StatusPeriodo;

public interface PeriodoLetivoRepository
        extends JpaRepository<PeriodoLetivo, Long> {

    List<PeriodoLetivo> findByUsuario_IdOrderByDataInicioDesc(
        Long usuarioId
    );

    Optional<PeriodoLetivo> findByIdAndUsuario_Id(
        Long id,
        Long usuarioId
    );

    boolean existsByUsuario_IdAndStatus(
        Long usuarioId,
        StatusPeriodo status
    );

    boolean existsByUsuario_IdAndNomeIgnoreCase(
    Long usuarioId,
    String nome
    );
}