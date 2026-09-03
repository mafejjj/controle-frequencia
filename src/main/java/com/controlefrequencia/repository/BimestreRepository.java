package com.controlefrequencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlefrequencia.entity.Bimestre;

public interface BimestreRepository
        extends JpaRepository<Bimestre, Long> {

    List<Bimestre> findByPeriodoLetivo_IdOrderByNumeroAsc(
        Long periodoLetivoId
    );

    boolean existsByPeriodoLetivo_IdAndNumero(
        Long periodoLetivoId,
        Integer numero
    );
}